package com.example.taskhub.Contact;

import com.example.taskhub.Contact.DTO.CreateContactDTO;
import com.example.taskhub.Directory.Directory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String full_name;
    private String phone;
    private String email;
    private String alt_phone;
    private String alt_email;
    private String company;
    private String company_position;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @JsonIgnore
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Directory> directory = new ArrayList<>();

    public Contact() { }

    public Contact(CreateContactDTO newContact) {
            this.full_name = newContact.getFull_name();
            this.phone = newContact.getPhone();
            this.email = newContact.getEmail();
            this.alt_email = newContact.getAlt_email();
            this.alt_phone = newContact.getAlt_phone();
            this.company = newContact.getCompany();
            this.company_position = newContact.getCompany_position();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlt_phone() {
        return alt_phone;
    }

    public void setAlt_phone(String alt_phone) {
        this.alt_phone = alt_phone;
    }

    public String getAlt_email() {
        return alt_email;
    }

    public void setAlt_email(String alt_email) {
        this.alt_email = alt_email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_position() {
        return company_position;
    }

    public void setCompany_position(String company_position) {
        this.company_position = company_position;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
