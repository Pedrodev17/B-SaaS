package org.example.bsaas.service;

import org.example.bsaas.model.DealContact;
import org.example.bsaas.repository.DealContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealContactService {

    @Autowired
    private DealContactRepository dealContactRepository;

    public List<DealContact> getAllDealContacts() {
        return dealContactRepository.findAll();
    }

    public DealContact getDealContactById(Integer id) {
        return dealContactRepository.findById(id).orElse(null);
    }

    public DealContact createDealContact(DealContact dealContact) {
        return dealContactRepository.save(dealContact);
    }

    public DealContact updateDealContact(Integer id, DealContact dealContactDetails) {
        Optional<DealContact> optionalDealContact = dealContactRepository.findById(id);
        if (optionalDealContact.isPresent()) {
            DealContact dealContact = optionalDealContact.get();
            dealContact.setDeal(dealContactDetails.getDeal());
            dealContact.setContact(dealContactDetails.getContact());
            // Adicione outros campos relevantes
            return dealContactRepository.save(dealContact);
        } else {
            return null;
        }
    }

    public boolean deleteDealContact(Integer id) {
        if (dealContactRepository.existsById(id)) {
            dealContactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}