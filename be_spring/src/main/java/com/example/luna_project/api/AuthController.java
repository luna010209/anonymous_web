package com.example.luna_project.api;

import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<Long> registerUser(
            @Valid @RequestBody SignupRequest request
    ){
        Long newId = authService.registerUser(request);
        return ResponseEntity.ok(newId);
    }
}
