package com.example.student_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // Make sure this is imported
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // The problem was likely here.
    // This declaration MUST be inside the class, but outside any method.
    @Autowired
    private RestTemplate restTemplate;

    // 1. Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Get student by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // 3. Register a student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // 4. Update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());

        return studentRepository.save(student);
    }

    // 5. REPORT CARD ENDPOINT (Connects to Grading Service)

    @GetMapping("/{id}/report-card")
    @CircuitBreaker(name = "gradingService", fallbackMethod = "fallbackReportCard")
    public Map<String, Object> getStudentReportCard(@PathVariable Long id) {
        // Codul tau existent care apeleaza http://grading-service/grades/...
        Student student = studentRepository.findById(id).orElseThrow();
        String gradingServiceUrl = "http://grading-service/grades/student/" + id;

        GradeDTO[] gradesArray = restTemplate.getForObject(gradingServiceUrl, GradeDTO[].class);
        // ... restul logicii ...

        Map<String, Object> response = new HashMap<>();
        response.put("studentName", student.getFirstName());
        response.put("grades", Arrays.asList(gradesArray));
        return response;
    }

    // METODA DE FALLBACK - Se apeleaza daca Grading Service e picat
    public Map<String, Object> fallbackReportCard(Long id, Throwable t) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sistemul de note este momentan indisponibil. Incercati mai tarziu.");
        response.put("studentId", id);
        response.put("status", "Circuit Breaker Active");
        return response;
    }
}