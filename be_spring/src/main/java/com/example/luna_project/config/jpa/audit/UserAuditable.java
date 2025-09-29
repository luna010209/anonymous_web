package com.example.luna_project.config.jpa.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class UserAuditable extends CommonAuditFields {
    private Integer createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void applyUserCreated(Integer currentUser){
        createdBy = currentUser;
    }
}
