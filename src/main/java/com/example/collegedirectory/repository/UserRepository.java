package com.example.collegedirectory.repository;

import com.example.collegedirectory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContainingOrNameContaining(String query, String query1);
}