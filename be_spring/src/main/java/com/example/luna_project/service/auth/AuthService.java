package com.example.luna_project.service.auth;

import com.example.luna_project.dto.auth.SignupRequest;

public interface AuthService {
    Long registerUser(SignupRequest request);
}
