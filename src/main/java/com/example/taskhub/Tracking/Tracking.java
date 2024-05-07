package com.example.taskhub.Tracking;

import com.example.taskhub.Tracking.DTO.CreateTrackingDTO;
import com.example.taskhub.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 50)
    private String title;
    @Column(length = 100)
    private String summary;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    public Tracking () { }

    public Tracking(CreateTrackingDTO newTracking, Project project) {
        this.title = newTracking.getTitle();
        this.summary = newTracking.getSummary();
        this.description = newTracking.getDescription();
        this.project = project;
        this.createdBy = newTracking.getCreatedBy();
    }

    @PrePersist
    public void prePersist() { this.createdAt = LocalDate.now(); }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDate.now(); }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
