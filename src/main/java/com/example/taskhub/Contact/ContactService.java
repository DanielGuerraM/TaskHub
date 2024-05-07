package com.example.taskhub.Contact;

import com.example.taskhub.Contact.DTO.CreateContactDTO;
import com.example.taskhub.Exceptions.ContactExceptions.ContactNotFoundException;
import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import com.example.taskhub.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, ProjectRepository projectRepository) {
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Object> retrieveContacts() {
        List<Contact> contacts = contactRepository.findContactsByDeletedAtIsNull();

        ServiceResponse response = new ServiceResponse();

        if(contacts.isEmpty()) {
            response.setSuccess(true);
            response.setMessage("No contact created");
            response.setData(contacts);

            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setMessage("Contacts were successfully retrived");
        response.setData(contacts);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> retrieveContactById(String idContact) {
        Contact contact = this.contactRepository.findContactByIdAndDeletedAtIsNull(idContact).orElseThrow(
                () -> new ContactNotFoundException("The contact does not exist",
                        new ExceptionsDetails(false, "The contact you are trying to found does not exist", null))
        );

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("contact found");
        response.setData(contact);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> createContact(CreateContactDTO contact) {
        Contact newContact = new Contact(contact);
        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("The contact has been successfully created");
        response.setData(contactRepository.save(newContact));

        return ResponseEntity.ok(response);
    }
}
