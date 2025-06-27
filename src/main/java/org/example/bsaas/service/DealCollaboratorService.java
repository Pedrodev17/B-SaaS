package org.example.bsaas.service;

import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.repository.DealCollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealCollaboratorService {

    @Autowired
    private DealCollaboratorRepository dealCollaboratorRepository;

    public List<DealCollaborator> getAllDealCollaborators() {
        return dealCollaboratorRepository.findAll();
    }

    public DealCollaborator getDealCollaboratorById(Integer id) {
        return dealCollaboratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id));
    }

    @Transactional
    public DealCollaborator createDealCollaborator(DealCollaborator dealCollaborator) {
        // Adicione validações de entidades relacionadas se necessário
        return dealCollaboratorRepository.save(dealCollaborator);
    }

    @Transactional
    public DealCollaborator updateDealCollaborator(Integer id, DealCollaborator dealCollaboratorDetails) {
        DealCollaborator dealCollaborator = dealCollaboratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id));
        dealCollaborator.setDeal(dealCollaboratorDetails.getDeal());
        dealCollaborator.setUser(dealCollaboratorDetails.getUser());
        // Adicione outros campos relevantes
        return dealCollaboratorRepository.save(dealCollaborator);
    }

    @Transactional
    public void deleteDealCollaborator(Integer id) {
        if (!dealCollaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id);
        }
        dealCollaboratorRepository.deleteById(id);
    }
}