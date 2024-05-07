package com.example.taskhub.project.DTO;

import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;

public class CreateProjectDTO {
    @NotNull(message = "The title is required")
    @NotBlank(message = "The title cannot be empty")
    public String title;
    public String description;
    @FutureOrPresent(message = "The start_date must be a valid date")
    public LocalDate start_date;
    @FutureOrPresent(message = "The finish_date must be a valid date")
    public LocalDate finish_date;
    @NotNull(message = "The created_by field is required")
    @NotBlank(message = "The created_by field cannot be empty")
    public String created_by;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
