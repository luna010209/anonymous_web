package com.example.luna_project.config.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

import static com.example.luna_project.config.jwt.TokenUtils.AUTHORITIES_KEY;

public class TokenProvider {
    private final SecretKey key;
    private final CustomUserDetailsService userDetailsService;

    public TokenProvider(JwtProperties jwtProperties, CustomUserDetailsService userDetailsService){
        this.key = TokenUtils.generateSignKey(jwtProperties.secretKey());
        this.userDetailsService = userDetailsService;
    }

    public static String releaseToken(
            Authentication authentication,
            JwtProperties jwtProperties
    ){
        SecretKey signingKey = TokenUtils.generateSignKey(jwtProperties.secretKey());
        Instant issuedAt = Instant.now();
        Date expirationDate = Date.from(issuedAt.plus(jwtProperties.expirationMinutes(), ChronoUnit.MINUTES));

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(Date.from(issuedAt))
                .expiration(expirationDate)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(signingKey)
                .compact();
    }

    public static String refreshToken(
            Authentication authentication,
            JwtProperties jwtProperties
    ){

    }
}
