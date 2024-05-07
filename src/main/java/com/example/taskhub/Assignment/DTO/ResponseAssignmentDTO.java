package com.example.taskhub.Assignment.DTO;

import java.util.List;

public class ResponseAssignmentDTO {
    public String project_id;
    public String project_title;
    public String status;
    public List<ResponseUsersDTO> users;

    public String getProject_id() {
        return project_id;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getStatus() {
        return status;
    }

    public List<ResponseUsersDTO> getUsers() {
        return users;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsers(List<ResponseUsersDTO> users) {
        this.users = users;
    }
}

