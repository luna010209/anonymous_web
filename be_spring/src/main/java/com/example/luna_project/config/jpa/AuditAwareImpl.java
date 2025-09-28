package com.example.luna_project.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication==null || !authentication.isAuthenticated()){
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        return Optional.of(0);
    }
}
