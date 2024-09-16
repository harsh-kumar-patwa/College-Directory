package com.example.collegedirectory.exception;

public class ResourceNotFoundException extends CollegeDirectoryException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
