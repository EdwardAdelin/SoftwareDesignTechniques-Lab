package com.example.professor_service;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ElementCollection // Simple list of Course IDs stored in a side table
    private List<Long> assignedCourseIds;
}