package org.example.bsaas.controller;

import org.example.bsaas.dto.ContactRequestDTO;
import org.example.bsaas.dto.ContactResponseDTO;
import org.example.bsaas.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<ContactResponseDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Integer id) {
        ContactResponseDTO contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@Validated @RequestBody ContactRequestDTO contactRequest) {
        ContactResponseDTO created = contactService.createContact(contactRequest);
        return ResponseEntity
                .created(URI.create("/api/contacts/" + created.getContactId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @PathVariable Integer id,
            @Validated @RequestBody ContactRequestDTO contactRequest) {
        ContactResponseDTO updated = contactService.updateContact(id, contactRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}