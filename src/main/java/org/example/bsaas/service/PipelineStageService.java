package org.example.bsaas.service;

import org.example.bsaas.exception.ResourceNotFoundException;
import org.example.bsaas.model.PipelineStage;
import org.example.bsaas.repository.PipelineStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PipelineStageService {

    @Autowired
    private PipelineStageRepository pipelineStageRepository;

    /**
     * Retorna todos os estágios do pipeline.
     */
    public List<PipelineStage> getAllStages() {
        return pipelineStageRepository.findAll();
    }

    /**
     * Retorna o estágio pelo id ou lança exceção se não encontrado.
     */
    public PipelineStage getStageById(Integer id) {
        return pipelineStageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id));
    }

    /**
     * Cria um novo estágio no pipeline.
     */
    @Transactional
    public PipelineStage createStage(PipelineStage stage) {
        return pipelineStageRepository.save(stage);
    }

    /**
     * Atualiza um estágio existente pelo id.
     */
    @Transactional
    public PipelineStage updateStage(Integer id, PipelineStage stageDetails) {
        PipelineStage stage = pipelineStageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id));
        stage.setStageName(stageDetails.getStageName());
        stage.setStageOrder(stageDetails.getStageOrder());
        stage.setDescription(stageDetails.getDescription());
        // Adicione outros campos conforme o seu modelo
        return pipelineStageRepository.save(stage);
    }

    /**
     * Remove um estágio por id, lança exceção se não existir.
     */
    @Transactional
    public void deleteStage(Integer id) {
        if (!pipelineStageRepository.existsById(id)) {
            throw new ResourceNotFoundException("PipelineStage não encontrado para o id: " + id);
        }
        pipelineStageRepository.deleteById(id);
    }
}