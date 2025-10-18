package com.example.luna_project.config.jwt;

public record JwtProperties(
        int expirationMinutes,
        int refreshExpirationMinutes,
        String secretKey
) {
}
