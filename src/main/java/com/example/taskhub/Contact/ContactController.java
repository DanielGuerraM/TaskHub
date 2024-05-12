package com.example.taskhub.Contact;

import com.example.taskhub.Contact.DTO.CreateContactDTO;
import com.example.taskhub.Contact.DTO.UpdateContactDTO;
import com.example.taskhub.Exceptions.ContactExceptions.ContactNotFoundException;
import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Object> retrieveContacts() {
        return this.contactService.retrieveContacts();
    }

    @GetMapping(path = "{idContact}")
    public ResponseEntity<Object> retriveContactById(@PathVariable("idContact") String idContact) throws ContactNotFoundException {
        return this.contactService.retrieveContactById(idContact);
    }

    @PostMapping
    public ResponseEntity<Object> createContact(@RequestBody @Valid CreateContactDTO contact, BindingResult result) {
        if(result.hasErrors()) {
            ServiceResponse response = new ServiceResponse();

            response.setSuccess(false);
            response.setMessage("Validation error");
            response.setData(result.getAllErrors());

            return ResponseEntity.badRequest().body(response);
        }

        return this.contactService.createContact(contact);
    }

    @PatchMapping(path = "{idContact}")
    public ResponseEntity<Object> updateContact(@PathVariable("idContact") String idContact, @RequestBody UpdateContactDTO contact) throws ContactNotFoundException {
        return this.contactService.updateContact(contact, idContact);
    }

    @DeleteMapping(path = "{idContact}")
    public ResponseEntity<Object> deleteContact(@PathVariable("idContact") String idContact) throws ContactNotFoundException {
        return this.contactService.deleteContact(idContact);
    }
}
