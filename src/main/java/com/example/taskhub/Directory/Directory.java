package com.example.taskhub.Directory;

import jakarta.persistence.*;

@Entity
@Table
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String project_id;
    private String contact_id;
}
