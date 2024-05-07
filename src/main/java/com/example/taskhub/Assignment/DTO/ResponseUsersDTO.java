package com.example.taskhub.Assignment.DTO;

public class ResponseUsersDTO {
    public String id;
    public String username;
    public String names;
    public String last_names;
    public String email;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNames() {
        return names;
    }

    public String getLast_names() {
        return last_names;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
