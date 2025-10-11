package com.example.luna_project.config.jwt;

import com.example.luna_project.entity.auth.Auth;
import com.example.luna_project.exception.CustomException;
import com.example.luna_project.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Auth user = authRepo.findByEmail(email).orElseThrow(
                ()-> new CustomException(HttpStatus.NOT_FOUND, "Not found email")
        );
        return new CustomUserDetails(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities (Auth user){
        return user.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
