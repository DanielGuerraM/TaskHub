package com.example.taskhub.Task.DTO;

import com.example.taskhub.Task.Task;
import com.example.taskhub.project.Project;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public class ResponseTaskDTO {
    private String project_id;
    private String title;
    private String status;
    private LocalDate start_date;
    private LocalDate finish_date;
    private List<Task> tasks;

    public ResponseTaskDTO(Project project){
        this.setProject_id(project.getId());
        this.setTitle(project.getTitle());
        this.setStatus(project.getStatus());
        this.setStart_date(project.getStart_date());
        this.setFinish_date(project.getFinish_date());
    }

    public String getProject_id() {
        return project_id;
    }

    public String getTitle() {
        return title;
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

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
