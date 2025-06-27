package org.example.bsaas.service;

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

@Service
public class DealContactService {

    @Autowired
    private DealContactRepository dealContactRepository;

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private ContactRepository contactRepository;

    public List<DealContact> getAllDealContacts() {
        return dealContactRepository.findAll();
    }

    public DealContact getDealContactById(Integer id) {
        return dealContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealContact não encontrado para o id: " + id));
    }

    @Transactional
    public DealContact createDealContact(DealContact dealContact) {
        // Validação das entidades relacionadas
        validateAndSetDealAndContact(dealContact);
        return dealContactRepository.save(dealContact);
    }

    @Transactional
    public DealContact updateDealContact(Integer id, DealContact dealContactDetails) {
        DealContact dealContact = dealContactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealContact não encontrado para o id: " + id));
        dealContact.setDeal(dealContactDetails.getDeal());
        dealContact.setContact(dealContactDetails.getContact());
        validateAndSetDealAndContact(dealContact);
        return dealContactRepository.save(dealContact);
    }

    @Transactional
    public void deleteDealContact(Integer id) {
        if (!dealContactRepository.existsById(id)) {
            throw new ResourceNotFoundException("DealContact não encontrado para o id: " + id);
        }
        dealContactRepository.deleteById(id);
    }

    private void validateAndSetDealAndContact(DealContact dealContact) {
        // Validação de Deal
        Deal deal = dealRepository.findById(dealContact.getDeal().getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal não encontrado para o id: " + dealContact.getDeal().getDealId()));
        dealContact.setDeal(deal);

        // Validação de Contact
        Contact contact = contactRepository.findById(dealContact.getContact().getContactId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact não encontrado para o id: " + dealContact.getContact().getContactId()));
        dealContact.setContact(contact);
    }
}