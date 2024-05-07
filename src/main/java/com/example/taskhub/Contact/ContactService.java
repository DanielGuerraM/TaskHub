package com.example.taskhub.Contact;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import com.example.taskhub.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, ProjectRepository projectRepository) {
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Object> createContact(String idProject) {

    }
}
