package com.example.luna_project.repo;

import com.example.luna_project.entity.auth.HistoryLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryLoginRepo extends JpaRepository<HistoryLogin, Long> {
}
