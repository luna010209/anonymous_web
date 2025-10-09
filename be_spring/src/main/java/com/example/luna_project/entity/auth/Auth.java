package com.example.luna_project.entity.auth;

import com.example.luna_project.config.jpa.audit.UserAuditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "aw_auth")
public class Auth extends UserAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String phone;
    @Setter
    private String email;
    @Setter
    private String password;

    @Setter
    private LocalDateTime lastLogin;

    @Column(name = "role")
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
