package com.example.collegedirectory.dto;

import lombok.Data;

@Data
public class StudentProfileDTO {
    private Long id;
    private String name;
    private String photo;
    private String email;
    private String department;
    private String year;
}