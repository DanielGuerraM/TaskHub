package com.example.taskhub.Contact;

import com.example.taskhub.Contact.DTO.CreateContactDTO;
import com.example.taskhub.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Object> retrieveContacts() {
        return this.contactService.retrieveContacts();
    }

    @GetMapping(path = "{idContact}")
    public ResponseEntity<Object> retriveContactById(@PathVariable("idContact") String idContact) {
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
}
