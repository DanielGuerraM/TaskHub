package com.example.taskhub.Task.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateTaskDTO {
    @NotNull(message = "The title field is required")
    @NotBlank(message = "The title field cannot be empty")
    private String title;
    private String description;

    @NotNull(message = "The duration field is required")
    @Min(value = 0, message = "The duration cannot be less than 0")
    private int duration;
    @NotNull(message = "The expiration date field is required")
    @FutureOrPresent(message = "The expiration date is not valid")
    private LocalDate expiration_date;
    @NotNull(message = "The user id is required")
    @NotBlank(message = "The user id cannot be empty")
    private String user_id;
    @NotNull(message = "The project id is required")
    @NotBlank(message = "The project id cannot be empty")
    private String project_id;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
