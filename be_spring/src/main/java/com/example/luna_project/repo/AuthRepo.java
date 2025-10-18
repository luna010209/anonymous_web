package com.example.luna_project.repo;

import com.example.luna_project.entity.auth.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<Auth, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    Optional<Auth> findByEmail(String email);
    Optional<Auth> findByPhone(String phone);

}
