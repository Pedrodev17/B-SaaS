package org.example.bsaas.service;

import org.example.bsaas.Entities.Contact;
import org.example.bsaas.Repositories.ContactRepository;
// Potentially import User entity if you need to associate contacts with users during creation/update
import org.example.bsaas.model.User;
import org.example.bsaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Exception for when a resource is not found
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
public class ContactService {

    private final ContactRepository contactRepository;
    // Uncomment if you need UserRepository
    // private final UserRepository userRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository /*, UserRepository userRepository */) {
        this.contactRepository = contactRepository;
        // this.userRepository = userRepository;
    }

    /**
     * Retrieves all contacts.
     * @return A list of all contacts.
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Retrieves a contact by its ID.
     * @param id The ID of the contact to retrieve.
     * @return An Optional containing the contact if found, or an empty Optional otherwise.
     */
    public Optional<Contact> getContactById(Integer id) {
        return contactRepository.findById(id);
    }

    /**
     * Creates a new contact.
     * Potentially, you might want to associate the contact with the currently authenticated user
     * or a specific owner user ID passed in the request.
     * @param contact The contact entity to create.
     * @return The created contact.
     */
    @Transactional
    public Contact createContact(Contact contact) {
        // Add any business logic before saving, e.g., validation, setting default owner
        // Example: if (contact.getOwnerUser() == null && SecurityContextHolder.getContext().getAuthentication() != null) {
        // User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        // .orElse(null); // Or handle more gracefully
        // contact.setOwnerUser(currentUser);
        // }
        return contactRepository.save(contact);
    }

    /**
     * Updates an existing contact.
     * @param id The ID of the contact to update.
     * @param contactDetails The contact entity with updated information.
     * @return The updated contact.
     * @throws ResponseStatusException if the contact with the given ID is not found.
     */
    @Transactional
    public Contact updateContact(Integer id, Contact contactDetails) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));

        // Update fields from contactDetails to existingContact
        // Be careful about null values in contactDetails if you don't want to overwrite existing data with null
        existingContact.setFirstName(contactDetails.getFirstName());
        existingContact.setLastName(contactDetails.getLastName());
        existingContact.setEmail(contactDetails.getEmail());
        existingContact.setPhoneNumber(contactDetails.getPhoneNumber());
        existingContact.setCompanyName(contactDetails.getCompanyName());
        existingContact.setJobTitle(contactDetails.getJobTitle());
        existingContact.setAddressStreet(contactDetails.getAddressStreet());
        existingContact.setAddressCity(contactDetails.getAddressCity());
        existingContact.setAddressState(contactDetails.getAddressState());
        existingContact.setAddressZipCode(contactDetails.getAddressZipCode());
        existingContact.setAddressCountry(contactDetails.getAddressCountry());
        existingContact.setLeadSource(contactDetails.getLeadSource());
        existingContact.setNotes(contactDetails.getNotes());

        // Handle ownerUser update if applicable
        if (contactDetails.getOwnerUser() != null) {
            // You might want to fetch the User entity from the DB if only an ID is provided
            // or ensure the User object is valid.
            existingContact.setOwnerUser(contactDetails.getOwnerUser());
        }

        return contactRepository.save(existingContact);
    }

    /**
     * Deletes a contact by its ID.
     * @param id The ID of the contact to delete.
     * @throws ResponseStatusException if the contact with the given ID is not found.
     */
    @Transactional
    public void deleteContact(Integer id) {
        if (!contactRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    // --- Additional useful methods based on ContactRepository ---

    /**
     * Finds a contact by their email address.
     * @param email The email address to search for.
     * @return An Optional containing the contact if found.
     */
    public Optional<Contact> getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    /**
     * Finds all contacts associated with a specific owner user ID.
     * @param ownerUserId The ID of the owner user.
     * @return A list of contacts owned by the specified user.
     */
    public List<Contact> getContactsByOwnerUserId(Integer ownerUserId) {
        return contactRepository.findByOwnerUserUserId(ownerUserId);
    }

    /**
     * Finds contacts whose first name contains the given string, case-insensitive.
     * @param firstName The string to search for in the first name.
     * @return A list of matching contacts.
     */
    public List<Contact> searchContactsByFirstName(String firstName) {
        return contactRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    /**
     * Finds contacts whose last name contains the given string, case-insensitive.
     * @param lastName The string to search for in the last name.
     * @return A list of matching contacts.
     */
    public List<Contact> searchContactsByLastName(String lastName) {
        return contactRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    /**
     * Finds contacts by company name, case-insensitive.
     * @param companyName The company name to search for.
     * @return A list of contacts belonging to the specified company.
     */
    public List<Contact> searchContactsByCompanyName(String companyName) {
        return contactRepository.findByCompanyNameIgnoreCase(companyName);
    }
}