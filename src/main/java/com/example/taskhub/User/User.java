package com.example.taskhub.User;

import com.example.taskhub.Assignment.Assignment;
import com.example.taskhub.Task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String username;
    private String names;
    private String last_names;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    @Null
    private LocalDate email_verified;
    @Null
    private String image;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignment = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> task = new ArrayList<>();

    public User() { }

    public User(String username, String names, String last_names, String phone, String email, String password) {
        this.username = username;
        this.names = names;
        this.last_names = last_names;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    public void prePersist() { createdAt = LocalDate.now(); }

    @PreUpdate
    public void preUpdate() { updatedAt = LocalDate.now(); }

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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getEmail_verified() {
        return email_verified;
    }

    public String getImage() {
        return image;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail_verified(LocalDate email_verified) {
        this.email_verified = email_verified;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deleted_at) {
        this.deletedAt = deleted_at;
    }
}
