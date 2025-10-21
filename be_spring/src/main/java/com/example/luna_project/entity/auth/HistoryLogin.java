package com.example.luna_project.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "aw_history_login")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Auth user;

    private String ipAddress;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    private HistoryLogin(Auth user, String ipAddress){
        this.user = user;
        this.ipAddress = ipAddress;
    }
}
