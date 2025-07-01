package org.example.bsaas.service;

import org.example.bsaas.dto.PipelineStageRequestDTO;
import org.example.bsaas.dto.PipelineStageResponseDTO;
import org.example.bsaas.model.PipelineStage;

public class PipelineStageMapper {

    public static PipelineStage toEntity(PipelineStageRequestDTO dto) {
        PipelineStage entity = new PipelineStage();
        entity.setStageName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static PipelineStageResponseDTO toDTO(PipelineStage entity) {
        PipelineStageResponseDTO dto = new PipelineStageResponseDTO();
        dto.setId(entity.getStageId());
        dto.setName(entity.getStageName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static void updateEntityFromDTO(PipelineStage entity, PipelineStageRequestDTO dto) {
        entity.setStageName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}