package com.example.student_service;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Double gradeValue; // Must match the field name in Grading Service
}
