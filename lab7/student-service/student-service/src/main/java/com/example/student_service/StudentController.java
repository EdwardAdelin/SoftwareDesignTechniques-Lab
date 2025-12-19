package com.example.student_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // Make sure this is imported

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
    public Map<String, Object> getStudentReportCard(@PathVariable Long id) {
        // A. Get Student from Local DB
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // B. Call Grading Service (Synchronous REST call)
        String gradingServiceUrl = "http://grading-service:8084/grades/student/" + id;

        // We use the restTemplate here to make the call
        GradeDTO[] gradesArray = restTemplate.getForObject(gradingServiceUrl, GradeDTO[].class);
        List<GradeDTO> grades = Arrays.asList(gradesArray);

        // C. Combine data
        Map<String, Object> response = new HashMap<>();
        response.put("studentName", student.getFirstName() + " " + student.getLastName());
        response.put("email", student.getEmail());
        response.put("grades", grades);

        return response;
    }
}