package com.example.luna_project.api;

import com.example.luna_project.dto.auth.LoginRequest;
import com.example.luna_project.dto.auth.LoginResponse;
import com.example.luna_project.dto.auth.SignupRequest;
import com.example.luna_project.service.auth.AuthService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        String ipAddress = getClientIp(httpServletRequest);

        LoginResponse loginResponse = authService.authenticate(request, ipAddress);
        httpServletResponse.setHeader("Authorization", "Bearer "+ loginResponse.accessToken());
        return ResponseEntity.ok(loginResponse);
    }

    public String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");

        if (ip==null || ip.isEmpty()) ip = request.getHeader("X-FORWARDED-FOR");

        if (ip==null || ip.isEmpty()) ip = request.getRemoteAddr();

        if (StringUtils.isNotBlank(ip)){
            List<String> ips = Arrays.asList(ip.split(","));
            ip = ips.get(0).trim();
        }
        return ip;
    }
}
