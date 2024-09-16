package com.example.collegedirectory.service.ServiceInterface;

import com.example.collegedirectory.dto.AuthenticationRequest;
import com.example.collegedirectory.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}