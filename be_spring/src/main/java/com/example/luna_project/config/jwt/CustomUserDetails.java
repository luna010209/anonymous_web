package com.example.luna_project.config.jwt;

import com.example.luna_project.entity.auth.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Auth user;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Auth user){
        this.user = user;
        this.authorities = user.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    public Auth getUser(){
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
