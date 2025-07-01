package org.example.bsaas.service;

import org.example.bsaas.dto.PipelineStageRequestDTO;
import org.example.bsaas.dto.PipelineStageResponseDTO;
import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.PipelineStage;
import org.example.bsaas.repository.PipelineStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PipelineStageService {

    @Autowired
    private PipelineStageRepository pipelineStageRepository;

    public List<PipelineStageResponseDTO> getAllStages() {
        return pipelineStageRepository.findAll().stream()
                .map(PipelineStageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PipelineStageResponseDTO getStageById(Integer id) {
        PipelineStage stage = pipelineStageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id));
        return PipelineStageMapper.toDTO(stage);
    }

    @Transactional
    public PipelineStageResponseDTO createStage(PipelineStageRequestDTO dto) {
        PipelineStage entity = PipelineStageMapper.toEntity(dto);
        PipelineStage saved = pipelineStageRepository.save(entity);
        return PipelineStageMapper.toDTO(saved);
    }

    @Transactional
    public PipelineStageResponseDTO updateStage(Integer id, PipelineStageRequestDTO dto) {
        PipelineStage stage = pipelineStageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id));
        PipelineStageMapper.updateEntityFromDTO(stage, dto);
        PipelineStage updated = pipelineStageRepository.save(stage);
        return PipelineStageMapper.toDTO(updated);
    }

    @Transactional
    public void deleteStage(Integer id) {
        if (!pipelineStageRepository.existsById(id)) {
            throw new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id);
        }
        pipelineStageRepository.deleteById(id);
    }
}