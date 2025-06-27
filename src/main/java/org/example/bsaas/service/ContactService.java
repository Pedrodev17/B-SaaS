package org.example.bsaas.service;

import org.example.bsaas.model.Contact;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.ContactRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.orElse(null);
    }

    public Contact createContact(Contact contact) {
        // Garantir que o OwnerUser está gerenciado (se existir)
        if (contact.getOwnerUser() != null && contact.getOwnerUser().getUserId() != null) {
            User owner = userRepository.findById(contact.getOwnerUser().getUserId().intValue()).orElse(null);
            contact.setOwnerUser(owner);
        }
        return contactRepository.save(contact);
    }

    public Contact updateContact(Integer id, Contact contactDetails) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFirstName(contactDetails.getFirstName());
            contact.setLastName(contactDetails.getLastName());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhoneNumber(contactDetails.getPhoneNumber());
            contact.setCompanyName(contactDetails.getCompanyName());
            contact.setJobTitle(contactDetails.getJobTitle());
            contact.setAddressStreet(contactDetails.getAddressStreet());
            contact.setAddressCity(contactDetails.getAddressCity());
            contact.setAddressState(contactDetails.getAddressState());
            contact.setAddressZipCode(contactDetails.getAddressZipCode());
            contact.setAddressCountry(contactDetails.getAddressCountry());
            contact.setLeadSource(contactDetails.getLeadSource());
            contact.setNotes(contactDetails.getNotes());

            // Atualiza OwnerUser, se fornecido
            if (contactDetails.getOwnerUser() != null && contactDetails.getOwnerUser().getUserId() != null) {
                User owner = userRepository.findById(contactDetails.getOwnerUser().getUserId().intValue()).orElse(null);
                contact.setOwnerUser(owner);
            }

            return contactRepository.save(contact);
        } else {
            return null;
        }
    }

    public boolean deleteContact(Integer id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}