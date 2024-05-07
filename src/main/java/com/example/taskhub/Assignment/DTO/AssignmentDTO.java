package com.example.taskhub.Assignment.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssignmentDTO {
    @NotBlank(message = "The user_id field cannot be empty")
    @NotNull(message = "The user_id field is required")
    public String user_id;
    @NotBlank(message = "The project_id field cannot be empty")
    @NotNull(message = "The project_id field is required")
    public String project_id;
    @NotBlank(message = "The role_id field cannot be empty")
    @NotNull(message = "The role_id field is required")
    public String role_id;

    public String getUser_id() {
        return user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
