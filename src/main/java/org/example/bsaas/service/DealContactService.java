package org.example.bsaas.service;

import org.example.bsaas.dto.DealContactRequestDTO;
import org.example.bsaas.dto.DealContactResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.DealContact;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.Contact;
import org.example.bsaas.repository.DealContactRepository;
import org.example.bsaas.repository.DealRepository;
import org.example.bsaas.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealContactService {

    @Autowired
    private DealContactRepository dealContactRepository;

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private ContactRepository contactRepository;

    public List<DealContactResponseDTO> getAllDealContacts() {
        return dealContactRepository.findAll().stream()
                .map(DealContactMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DealContactResponseDTO getDealContactById(Integer id) {
        DealContact dealContact = dealContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealContact não encontrado para o id: " + id));
        return DealContactMapper.toDTO(dealContact);
    }

    @Transactional
    public DealContactResponseDTO createDealContact(DealContactRequestDTO dto) {
        Deal deal = dealRepository.findById(dto.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal não encontrado para o id: " + dto.getDealId()));
        Contact contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact não encontrado para o id: " + dto.getContactId()));
        DealContact entity = DealContactMapper.toEntity(dto, deal, contact);
        DealContact saved = dealContactRepository.save(entity);
        return DealContactMapper.toDTO(saved);
    }

    @Transactional
    public DealContactResponseDTO updateDealContact(Integer id, DealContactRequestDTO dto) {
        DealContact dealContact = dealContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealContact não encontrado para o id: " + id));
        Deal deal = dealRepository.findById(dto.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal não encontrado para o id: " + dto.getDealId()));
        Contact contact = contactRepository.findById(dto.getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact não encontrado para o id: " + dto.getContactId()));
        dealContact.setDeal(deal);
        dealContact.setContact(contact);
        // roleInDeal pode ser incluído aqui se for adicionado ao DTO
        DealContact updated = dealContactRepository.save(dealContact);
        return DealContactMapper.toDTO(updated);
    }

    @Transactional
    public void deleteDealContact(Integer id) {
        if (!dealContactRepository.existsById(id)) {
            throw new ResourceNotFoundException("DealContact não encontrado para o id: " + id);
        }
        dealContactRepository.deleteById(id);
    }
}