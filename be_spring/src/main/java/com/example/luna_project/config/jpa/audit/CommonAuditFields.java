package com.example.luna_project.config.jpa.audit;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonAuditFields {
    public static final String DEFAULT_CONDITION = "is_deleted = false";

    @Getter
    @LastModifiedBy
    private Integer updatedBy;

    @Getter
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private boolean isDeleted = false;

    public boolean isDeleted(){
        return isDeleted;
    }

    public void delete(){
        isDeleted = true;
    }
}
