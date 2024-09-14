package com.example.collegedirectory.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administrator_profiles")
@Getter
@Setter
public class AdministratorProfile {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}