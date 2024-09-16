package com.example.collegedirectory.exception;

public class CollegeDirectoryException extends RuntimeException {

    public CollegeDirectoryException(String message) {
        super(message);
    }

    public CollegeDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
