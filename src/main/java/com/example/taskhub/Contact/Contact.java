package com.example.taskhub.Contact;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private LocalDate deletedAt;
}
