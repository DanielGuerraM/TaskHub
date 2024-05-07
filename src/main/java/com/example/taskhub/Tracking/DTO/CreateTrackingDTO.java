package com.example.taskhub.Tracking.DTO;

import com.example.taskhub.project.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTrackingDTO {
    @NotNull(message = "The title is required")
    @NotBlank(message = "The title cannot be blank")
    private String title;
    private String summary;
    @NotNull(message = "The description is required")
    @NotBlank(message = "The description cannot be blank")
    private String description;
    @NotNull(message = "The createdBy is required")
    @NotBlank(message = "The createdBy cannot be blank")
    private String createdBy;
    @NotNull(message = "The project id is required")
    @NotBlank(message = "The project id cannot be blank")
    private String project_id;

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

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
