package com.example.taskhub.Directory;

import com.example.taskhub.Contact.Contact;
import com.example.taskhub.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
