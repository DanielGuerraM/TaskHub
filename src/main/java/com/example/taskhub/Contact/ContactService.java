package com.example.taskhub.Contact;

import com.example.taskhub.Contact.DTO.CreateContactDTO;
import com.example.taskhub.Contact.DTO.UpdateContactDTO;
import com.example.taskhub.Exceptions.ContactExceptions.ContactNotFoundException;
import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.ProjectRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;

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

    public ResponseEntity<Object> retrieveContactById(String idContact) throws ContactNotFoundException {
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

    public ResponseEntity<Object> updateContact(UpdateContactDTO contact, String idContact) throws ContactNotFoundException {
        Contact existingContact = this.contactRepository.findContactByIdAndDeletedAtIsNull(idContact).orElseThrow(
                () -> new ContactNotFoundException("The contact does not exists",
                        new ExceptionsDetails(false, "The contact you are trying to update does not exists", null))
        );

        BeanUtils.copyProperties(contact, existingContact, "id");
        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("The contact has been updated successfully");
        response.setData(this.contactRepository.save(existingContact));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> deleteContact(String idContact) throws ContactNotFoundException {
        Contact existingContact = this.contactRepository.findContactByIdAndDeletedAtIsNull(idContact).orElseThrow(
                () -> new ContactNotFoundException("The contact does not exists",
                        new ExceptionsDetails(false, "The contact you are trying to delete does not exists", null))
        );

        existingContact.setDeletedAt(LocalDate.now());
        this.contactRepository.save(existingContact);

        return ResponseEntity.noContent().build();
    }
}