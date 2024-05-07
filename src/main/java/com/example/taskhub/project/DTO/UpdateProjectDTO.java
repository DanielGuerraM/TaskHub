package com.example.taskhub.project.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class UpdateProjectDTO {
    private String title;
    private String description;
    private String status;
    private LocalDate start_date;
    private LocalDate finish_date;
    private String updated_by;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
