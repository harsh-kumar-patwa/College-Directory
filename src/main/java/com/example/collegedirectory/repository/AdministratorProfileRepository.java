package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
    AdministratorProfile findByUserId(Long userId);
}