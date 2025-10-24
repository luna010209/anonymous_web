package com.example.luna_project.service.auth;

import com.example.luna_project.dto.auth.LoginRequest;
import com.example.luna_project.dto.auth.LoginResponse;
import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.dto.auth.UserInfo;

public interface AuthService {
    Long registerUser(SignupRequest request);

    LoginResponse authenticate(LoginRequest request, String ipAddress);

    UserInfo userLogin();
}
