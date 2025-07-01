package org.example.bsaas.service;

import org.example.bsaas.dto.ContactRequestDTO;
import org.example.bsaas.dto.ContactResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.Contact;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.ContactRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    // Retorna lista de DTOs
    public List<ContactResponseDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retorna DTO
    public ContactResponseDTO getContactById(Integer id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id: " + id));
        return ContactMapper.toDTO(contact);
    }

    // Cria a partir do DTO
    @Transactional
    public ContactResponseDTO createContact(ContactRequestDTO contactRequest) {
        // Busca o usuário dono
        User owner = userRepository.findById(contactRequest.getOwnerUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + contactRequest.getOwnerUserId()));
        Contact contact = ContactMapper.toEntity(contactRequest, owner);
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.toDTO(savedContact);
    }

    // Atualiza a partir do DTO
    @Transactional
    public ContactResponseDTO updateContact(Integer id, ContactRequestDTO contactRequest) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id: " + id));

        // Atualiza campos básicos
        if (contactRequest.getName() != null) {
            String[] parts = contactRequest.getName().split(" ", 2);
            contact.setFirstName(parts[0]);
            contact.setLastName(parts.length > 1 ? parts[1] : "");
        }
        contact.setEmail(contactRequest.getEmail());
        contact.setPhoneNumber(contactRequest.getPhone());

        // Atualiza Owner
        if (contactRequest.getOwnerUserId() != null) {
            User owner = userRepository.findById(contactRequest.getOwnerUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + contactRequest.getOwnerUserId()));
            contact.setOwnerUser(owner);
        }

        Contact updatedContact = contactRepository.save(contact);
        return ContactMapper.toDTO(updatedContact);
    }

    @Transactional
    public void deleteContact(Integer id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contato não encontrado com id: " + id);
        }
        contactRepository.deleteById(id);
    }
}