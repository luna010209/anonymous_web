package com.example.luna_project.service.auth;

import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.entity.auth.Auth;
import com.example.luna_project.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;
    @Override
    public Long registerUser(SignupRequest request) {
//        if (authRepo.existsByEmail(request.email()))
//            throw new BadRequestException("");
        Auth newUser = Auth.builder()
                .email(request.email())
                .phone(request.phone())
                .username(request.username())
                .build();
        return 0L;
    }
}
