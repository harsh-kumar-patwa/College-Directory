package com.example.collegedirectory.exception;


public class AuthenticationException extends CollegeDirectoryException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}