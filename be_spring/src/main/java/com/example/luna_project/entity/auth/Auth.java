package com.example.luna_project.entity.auth;

import com.example.luna_project.config.jpa.audit.UserAuditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}
