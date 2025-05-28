package org.example.bsaas.controller;

import org.example.bsaas.model.Contact;
import org.example.bsaas.model.User;
import org.example.bsaas.service.ContactService;
import org.example.bsaas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Integer id) {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        // Se OwnerUser vier com apenas o ID, vamos buscar o User completo
        if (contact.getOwnerUser() != null && contact.getOwnerUser().getUserId() != null) {
            User owner = userService.getUserById(contact.getOwnerUser().getUserId());
            contact.setOwnerUser(owner);
        }
        Contact created = contactService.createContact(contact);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contactDetails) {
        // Se OwnerUser vier com apenas o ID, vamos buscar o User completo
        if (contactDetails.getOwnerUser() != null && contactDetails.getOwnerUser().getUserId() != null) {
            User owner = userService.getUserById(contactDetails.getOwnerUser().getUserId());
            contactDetails.setOwnerUser(owner);
        }
        Contact updated = contactService.updateContact(id, contactDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        boolean deleted = contactService.deleteContact(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}