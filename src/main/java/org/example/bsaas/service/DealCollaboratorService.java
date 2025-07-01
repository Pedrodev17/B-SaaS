package org.example.bsaas.service;

import org.example.bsaas.dto.DealCollaboratorRequestDTO;
import org.example.bsaas.dto.DealCollaboratorResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.DealCollaboratorRepository;
import org.example.bsaas.repository.DealRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealCollaboratorService {

    @Autowired
    private DealCollaboratorRepository dealCollaboratorRepository;

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private UserRepository userRepository;

    public List<DealCollaboratorResponseDTO> getAllDealCollaborators() {
        return dealCollaboratorRepository.findAll().stream()
                .map(DealCollaboratorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DealCollaboratorResponseDTO getDealCollaboratorById(Integer id) {
        DealCollaborator dealCollaborator = dealCollaboratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id));
        return DealCollaboratorMapper.toDTO(dealCollaborator);
    }

    @Transactional
    public DealCollaboratorResponseDTO createDealCollaborator(DealCollaboratorRequestDTO dto) {
        Deal deal = dealRepository.findById(dto.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal não encontrado para o id: " + dto.getDealId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id: " + dto.getUserId()));
        DealCollaborator entity = DealCollaboratorMapper.toEntity(dto, deal, user);
        DealCollaborator saved = dealCollaboratorRepository.save(entity);
        return DealCollaboratorMapper.toDTO(saved);
    }

    @Transactional
    public DealCollaboratorResponseDTO updateDealCollaborator(Integer id, DealCollaboratorRequestDTO dto) {
        DealCollaborator dealCollaborator = dealCollaboratorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id));
        Deal deal = dealRepository.findById(dto.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal não encontrado para o id: " + dto.getDealId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id: " + dto.getUserId()));
        dealCollaborator.setDeal(deal);
        dealCollaborator.setUser(user);
        // roleInDeal pode ser incluído aqui se for adicionado ao DTO
        DealCollaborator updated = dealCollaboratorRepository.save(dealCollaborator);
        return DealCollaboratorMapper.toDTO(updated);
    }

    @Transactional
    public void deleteDealCollaborator(Integer id) {
        if (!dealCollaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("DealCollaborator não encontrado para o id: " + id);
        }
        dealCollaboratorRepository.deleteById(id);
    }
}