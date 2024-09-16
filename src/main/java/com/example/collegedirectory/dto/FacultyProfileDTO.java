package com.example.collegedirectory.dto;

import lombok.Data;

@Data
public class FacultyProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String officeHours;
    private String photo;

}