package com.example.taskhub.User.DTO;

import com.example.taskhub.User.User;
import com.example.taskhub.project.Project;
import jakarta.persistence.Column;

import java.util.List;

public class ResponseUserDTO {
    private String id;
    private String username;
    private String names;
    private String last_names;
    private String phone;
    private List<Project> projects;

    public  ResponseUserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setNames(user.getNames());
        setLast_names(user.getLast_names());
        setPhone(user.getPhone());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast_names() {
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
