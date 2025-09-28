package com.example.luna_project.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
public class JpaConfig {
    @Bean
    public AuditorAware<Integer> auditorAware(){
        return new AuditAwareImpl();
    }
}
