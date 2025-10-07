package com.example.luna_project.service.auth;

import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;
    @Override
    public Long registerUser(SignupRequest request) {
        return 0L;
    }
}
