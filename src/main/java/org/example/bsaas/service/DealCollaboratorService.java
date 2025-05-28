package org.example.bsaas.service;

import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.repository.DealCollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealCollaboratorService {

    @Autowired
    private DealCollaboratorRepository dealCollaboratorRepository;

    public List<DealCollaborator> getAllDealCollaborators() {
        return dealCollaboratorRepository.findAll();
    }

    public DealCollaborator getDealCollaboratorById(Integer id) {
        return dealCollaboratorRepository.findById(id).orElse(null);
    }

    public DealCollaborator createDealCollaborator(DealCollaborator dealCollaborator) {
        return dealCollaboratorRepository.save(dealCollaborator);
    }

    public DealCollaborator updateDealCollaborator(Integer id, DealCollaborator dealCollaboratorDetails) {
        Optional<DealCollaborator> optionalDealCollaborator = dealCollaboratorRepository.findById(id);
        if (optionalDealCollaborator.isPresent()) {
            DealCollaborator dealCollaborator = optionalDealCollaborator.get();
            dealCollaborator.setDeal(dealCollaboratorDetails.getDeal());
            dealCollaborator.setUser(dealCollaboratorDetails.getUser());
            // Adicione outros campos relevantes
            return dealCollaboratorRepository.save(dealCollaborator);
        } else {
            return null;
        }
    }

    public boolean deleteDealCollaborator(Integer id) {
        if (dealCollaboratorRepository.existsById(id)) {
            dealCollaboratorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}