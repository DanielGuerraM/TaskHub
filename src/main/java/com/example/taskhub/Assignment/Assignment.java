package com.example.taskhub.Assignment;

import com.example.taskhub.User.User;
import com.example.taskhub.project.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    @NotNull
    @NotBlank
    public String role_id;
    @PastOrPresent
    public LocalDate date_of_assignment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project project;

    @PrePersist
    public void prePersist() { date_of_assignment = LocalDate.now(); }

    public String getId() {
        return id;
    }

    public String getRole_id() {
        return role_id;
    }

    public LocalDate getDate_of_assignment() {
        return date_of_assignment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public void setDate_of_assignment(LocalDate date_of_assignment) {
        this.date_of_assignment = date_of_assignment;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public void setProject (Project project) {
        this.project = project;
    }
}