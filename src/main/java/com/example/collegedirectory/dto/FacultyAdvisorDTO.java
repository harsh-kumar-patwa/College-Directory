package com.example.collegedirectory.dto;

import lombok.Data;

@Data
public class FacultyAdvisorDTO {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String officeHours;
}