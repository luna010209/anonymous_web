package com.example.luna_project.config.jwt;

import com.example.luna_project.entity.auth.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final Auth user;
//    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Auth user){
        this.user = user;
//        this.authorities = user.get
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
