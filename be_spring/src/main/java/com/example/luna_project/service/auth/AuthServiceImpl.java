package com.example.luna_project.service.auth;

import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.entity.auth.Auth;
import com.example.luna_project.exception.CustomException;
import com.example.luna_project.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;
    private final PasswordEncoder encoder;
    @Override
    public Long registerUser(SignupRequest request) {
        if (authRepo.existsByEmail(request.email()))
            throw new CustomException(HttpStatus.CONFLICT, "This email already exists!!");
        if (authRepo.existsByPhone(request.phone()))
            throw new CustomException(HttpStatus.CONFLICT, "This phone number is already registered!!");
        if (authRepo.existsByUsername(request.username()))
            throw new CustomException(HttpStatus.CONFLICT, "This username already exists!!");
        if (!request.password().equals(request.cfPassword()))
            throw new CustomException(HttpStatus.CONFLICT, "Your confirm password does not match with your password.");

        Auth newUser = Auth.builder()
                .email(request.email())
                .phone(request.phone())
                .username(request.username())
                .password(encoder.encode(request.password()))
                .build();
        Auth savedUser = authRepo.save(newUser);
        return savedUser.getId();
    }
}
