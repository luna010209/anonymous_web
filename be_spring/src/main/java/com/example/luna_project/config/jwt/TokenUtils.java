package com.example.luna_project.config.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenUtils {
    public static final String AUTHORITIES_KEY = "auth";

    public static SecretKey generateSignKey(String secretKey){
        byte[] encodeKey = Base64.getEncoder().withoutPadding().encode(secretKey.getBytes(StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(encodeKey);
    }
}
