package com.example.luna_project.dto.auth;

import com.example.luna_project.entity.auth.Auth;
import com.example.luna_project.entity.auth.Role;

import java.util.List;

public record UserInfo(
        String username,
        String email,
        String phone,
        List<String> roles
) {
    public static UserInfo from(Auth user){
        return new UserInfo(
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRoles().stream().map(Role::getAuthority).toList()
        );
    }
}
