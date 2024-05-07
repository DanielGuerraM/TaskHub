package com.example.taskhub.Task;

import com.example.taskhub.Task.DTO.CreateTaskDTO;
import com.example.taskhub.Task.Enums.TaskStatus;
import com.example.taskhub.User.User;
import com.example.taskhub.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String status;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int duration;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private LocalDate completedAt;
    private LocalDate expirationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    public Task() { }

    public Task(CreateTaskDTO newTask, User user, Project project) {
        setTitle(newTask.getTitle());
        setDescription(newTask.getDescription());
        setDuration(newTask.getDuration());
        setStatus(TaskStatus.setTaskStatus(TaskStatus.TaskStatusList.TO_START));
        setProject(project);
        setUser(user);
        setExpirationDate(newTask.getExpiration_date());
    }

    @PrePersist
    public void prePersist(){ this.createdAt = LocalDate.now(); }

    @PreUpdate
    public void preUpdate(){ this.createdAt = LocalDate.now(); }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCompletedAt(LocalDate completedAt) {
        this.completedAt = completedAt;
    }

    public void setExpirationDate(LocalDate expiration_date) {
        this.expirationDate = expiration_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }
}
