package org.example.bsaas.service;

import org.example.bsaas.dto.DealRequestDTO;
import org.example.bsaas.dto.DealResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.DealRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealService {

    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    public DealService(DealRepository dealRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    // Retorna lista de DTOs
    public List<DealResponseDTO> getAllDeals() {
        return dealRepository.findAll()
                .stream()
                .map(DealMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retorna DTO
    public DealResponseDTO getDealById(Integer id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negócio não encontrado com id: " + id));
        return DealMapper.toDTO(deal);
    }

    // Cria a partir do DTO
    @Transactional
    public DealResponseDTO createDeal(DealRequestDTO dealRequest) {
        User owner = userRepository.findById(dealRequest.getOwnerUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + dealRequest.getOwnerUserId()));
        Deal deal = DealMapper.toEntity(dealRequest, owner);
        validarCamposObrigatorios(deal);
        Deal savedDeal = dealRepository.save(deal);
        return DealMapper.toDTO(savedDeal);
    }

    // Atualiza a partir do DTO
    @Transactional
    public DealResponseDTO updateDeal(Integer id, DealRequestDTO dealRequest) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Negócio não encontrado com id: " + id));

        // Atualiza campos básicos
        if (dealRequest.getTitle() != null) {
            deal.setTitle(dealRequest.getTitle());
        }
        if (dealRequest.getDescription() != null) {
            deal.setDescription(dealRequest.getDescription());
        }
        if (dealRequest.getAmount() != null) {
            deal.setValue(dealRequest.getAmount());
        }

        // Atualiza Owner
        if (dealRequest.getOwnerUserId() != null) {
            User owner = userRepository.findById(dealRequest.getOwnerUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário proprietário não encontrado com id: " + dealRequest.getOwnerUserId()));
            deal.setOwnerUser(owner);
        }

        validarCamposObrigatorios(deal);
        Deal updatedDeal = dealRepository.save(deal);
        return DealMapper.toDTO(updatedDeal);
    }

    @Transactional
    public void deleteDeal(Integer id) {
        if (!dealRepository.existsById(id)) {
            throw new ResourceNotFoundException("Negócio não encontrado com id: " + id);
        }
        dealRepository.deleteById(id);
    }

    // Validação igual ao original
    private void validarCamposObrigatorios(Deal deal) {
        if (deal.getTitle() == null || deal.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (deal.getValue() == null) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }
        // Adicione outras validações conforme necessário
    }
}