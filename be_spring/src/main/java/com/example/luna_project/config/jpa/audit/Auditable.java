package com.example.luna_project.config.jpa.audit;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public abstract class Auditable extends CommonAuditFields {
    @CreatedBy
    @Column(nullable = false, updatable = false)
    protected Integer createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
