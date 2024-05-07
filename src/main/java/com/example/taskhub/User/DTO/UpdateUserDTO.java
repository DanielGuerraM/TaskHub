package com.example.taskhub.User.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UpdateUserDTO {
    private String username;
    private String names;
    private String last_names;
    private String phone;
    private String image;
    private LocalDate email_verified;

    public String getUsername() {
        return username;
    }

    public String getNames() {
        return names;
    }

    public String getLast_names() {
        return last_names;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getEmail_verified() {
        return email_verified;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail_verified(LocalDate email_verified) {
        this.email_verified = email_verified;
    }
}
