package org.example.bsaas.service;

import org.example.bsaas.model.PipelineStage;
import org.example.bsaas.repository.PipelineStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PipelineStageService {

    @Autowired
    private PipelineStageRepository pipelineStageRepository;

    public List<PipelineStage> getAllStages() {
        return pipelineStageRepository.findAll();
    }

    public PipelineStage getStageById(Integer id) {
        return pipelineStageRepository.findById(id).orElse(null);
    }

    public PipelineStage createStage(PipelineStage stage) {
        return pipelineStageRepository.save(stage);
    }

    public PipelineStage updateStage(Integer id, PipelineStage stageDetails) {
        Optional<PipelineStage> optionalStage = pipelineStageRepository.findById(id);
        if (optionalStage.isPresent()) {
            PipelineStage stage = optionalStage.get();
            stage.setStageName(stageDetails.getStageName());
            stage.setStageOrder(stageDetails.getStageOrder());
            stage.setDescription(stageDetails.getDescription());
            // Adicione outros campos conforme o seu modelo
            return pipelineStageRepository.save(stage);
        } else {
            return null;
        }
    }

    public boolean deleteStage(Integer id) {
        if (pipelineStageRepository.existsById(id)) {
            pipelineStageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}