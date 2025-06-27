package org.example.bsaas.service;

import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.ContactRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id: " + id));
    }

    @Transactional
    public Contact createContact(Contact contact) {
        setAndValidateOwner(contact);
        return contactRepository.save(contact);
    }

    @Transactional
    public Contact updateContact(Integer id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id: " + id));

        updateContactFields(contact, contactDetails);
        setAndValidateOwner(contactDetails);
        contact.setOwnerUser(contactDetails.getOwnerUser());

        return contactRepository.save(contact);
    }

    @Transactional
    public void deleteContact(Integer id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contato não encontrado com id: " + id);
        }
        contactRepository.deleteById(id);
    }

    private void setAndValidateOwner(Contact contact) {
        if (contact.getOwnerUser() != null && contact.getOwnerUser().getUserId() != null) {
            User owner = userRepository.findById(contact.getOwnerUser().getUserId().intValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + contact.getOwnerUser().getUserId()));
            contact.setOwnerUser(owner);
        }
    }

    private void updateContactFields(Contact contact, Contact details) {
        contact.setFirstName(details.getFirstName());
        contact.setLastName(details.getLastName());
        contact.setEmail(details.getEmail());
        contact.setPhoneNumber(details.getPhoneNumber());
        contact.setCompanyName(details.getCompanyName());
        contact.setJobTitle(details.getJobTitle());
        contact.setAddressStreet(details.getAddressStreet());
        contact.setAddressCity(details.getAddressCity());
        contact.setAddressState(details.getAddressState());
        contact.setAddressZipCode(details.getAddressZipCode());
        contact.setAddressCountry(details.getAddressCountry());
        contact.setLeadSource(details.getLeadSource());
        contact.setNotes(details.getNotes());
    }
}