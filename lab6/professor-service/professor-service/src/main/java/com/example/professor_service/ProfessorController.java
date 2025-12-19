package com.example.professor_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    // Endpoint to assign a course to a professor
    @PostMapping("/{professorId}/assign-course/{courseId}")
    public Professor assignCourse(@PathVariable Long professorId, @PathVariable Long courseId) {

        // 1. Verify Professor Exists
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        // 2. Verify Course Exists (Call Course Service)
        String courseServiceUrl = "http://localhost:8082/courses/" + courseId;
        try {
            // If this throws an exception, the course doesn't exist (or service is down)
            restTemplate.getForObject(courseServiceUrl, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Course does not exist or Course Service is down");
        }

        // 3. Assign
        professor.getAssignedCourseIds().add(courseId);
        return professorRepository.save(professor);
    }
}