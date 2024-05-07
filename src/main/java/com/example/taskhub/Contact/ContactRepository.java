package com.example.taskhub.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, String> {
    List<Contact> findContactsByDeletedAtIsNull();
    Optional<Contact> findContactByIdAndDeletedAtIsNull(String idContact);
}
