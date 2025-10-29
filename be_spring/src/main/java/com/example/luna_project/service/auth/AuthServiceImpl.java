package com.example.luna_project.service.auth;

import com.example.luna_project.config.jwt.CustomUserDetails;
import com.example.luna_project.config.jwt.JwtProperties;
import com.example.luna_project.config.jwt.TokenProvider;
import com.example.luna_project.dto.auth.LoginRequest;
import com.example.luna_project.dto.auth.LoginResponse;
import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.dto.auth.UserInfo;
import com.example.luna_project.entity.auth.Auth;
import com.example.luna_project.entity.auth.HistoryLogin;
import com.example.luna_project.exception.CustomException;
import com.example.luna_project.repo.AuthRepo;
import com.example.luna_project.repo.HistoryLoginRepo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    private final AuthRepo authRepo;
    private final HistoryLoginRepo historyLoginRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtProperties jwtProperties;

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

    @Override
    public LoginResponse authenticate(LoginRequest request, String ipAddress) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.emailOrPhone(), request.password());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            Auth user = userDetails.getUser();

            user.setLastLogin(LocalDateTime.now());

            HistoryLogin historyLogin = HistoryLogin.builder()
                    .user(user)
                    .ipAddress(ipAddress)
                    .build();
            historyLoginRepo.save(historyLogin);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = TokenProvider.releaseToken(authentication, jwtProperties);
            String refreshToken = TokenProvider.refreshToken(authentication, jwtProperties);

            return new LoginResponse(accessToken, refreshToken);
        } catch (AuthenticationException e){
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Please check your email/mobile phone or password");
        }
    }

    @Override
    public UserInfo userLogin() {
        String emailOrPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        Auth user = null;
        if (authRepo.existsByEmail(emailOrPhone))
            user = authRepo.findByEmail(emailOrPhone).orElseThrow();
        else if (authRepo.existsByPhone(emailOrPhone))
            user = authRepo.findByPhone(emailOrPhone).orElseThrow();
        if (user == null)
            throw new CustomException(HttpStatus.NOT_FOUND, "No exist this user");
        return UserInfo.from(user);
    }
}
