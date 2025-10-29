package com.example.luna_project.dto.auth;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
