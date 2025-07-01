package org.example.bsaas.service;

import org.example.bsaas.dto.ContactRequestDTO;
import org.example.bsaas.dto.ContactResponseDTO;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.User;

public class ContactMapper {

    public static Contact toEntity(ContactRequestDTO dto, User ownerUser) {
        Contact contact = new Contact();
        if (dto.getName() != null) {
            String[] parts = splitName(dto.getName());
            contact.setFirstName(parts[0]);
            contact.setLastName(parts[1]);
        }
        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhone());
        contact.setOwnerUser(ownerUser);
        return contact;
    }

    public static ContactResponseDTO toDTO(Contact contact) {
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setContactId(contact.getContactId());
        dto.setName(joinName(contact.getFirstName(), contact.getLastName()));
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhoneNumber());
        if (contact.getOwnerUser() != null) {
            dto.setOwnerUserId(contact.getOwnerUser().getUserId());
            dto.setOwnerUserName(contact.getOwnerUser().getName());
        }
        return dto;
    }

    private static String[] splitName(String name) {
        String[] parts = name.split(" ", 2);
        return new String[]{parts[0], parts.length > 1 ? parts[1] : ""};
    }

    private static String joinName(String firstName, String lastName) {
        return (firstName != null ? firstName : "") +
                (lastName != null && !lastName.isBlank() ? " " + lastName : "").trim();
    }
}