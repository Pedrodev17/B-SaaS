package org.example.bsaas.controller;

import org.example.bsaas.dto.PipelineStageRequestDTO;
import org.example.bsaas.dto.PipelineStageResponseDTO;
import org.example.bsaas.service.PipelineStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pipelinestages")
public class PipelineStageController {

    @Autowired
    private PipelineStageService pipelineStageService;

    @GetMapping
    public List<PipelineStageResponseDTO> getAllStages() {
        return pipelineStageService.getAllStages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PipelineStageResponseDTO> getStageById(@PathVariable Integer id) {
        PipelineStageResponseDTO dto = pipelineStageService.getStageById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PipelineStageResponseDTO> createStage(
            @Validated @RequestBody PipelineStageRequestDTO request) {
        PipelineStageResponseDTO created = pipelineStageService.createStage(request);
        return ResponseEntity.created(URI.create("/api/pipelinestages/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PipelineStageResponseDTO> updateStage(
            @PathVariable Integer id, @Validated @RequestBody PipelineStageRequestDTO request) {
        PipelineStageResponseDTO updated = pipelineStageService.updateStage(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Integer id) {
        pipelineStageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}