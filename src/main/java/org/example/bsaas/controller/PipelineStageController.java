package org.example.bsaas.controller;

import org.example.bsaas.model.PipelineStage;
import org.example.bsaas.service.PipelineStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pipelinestages")
public class PipelineStageController {

    @Autowired
    private PipelineStageService pipelineStageService;

    @GetMapping
    public List<PipelineStage> getAllStages() {
        return pipelineStageService.getAllStages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PipelineStage> getStageById(@PathVariable Integer id) {
        PipelineStage stage = pipelineStageService.getStageById(id);
        if (stage != null) {
            return ResponseEntity.ok(stage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PipelineStage> createStage(@RequestBody PipelineStage stage) {
        PipelineStage created = pipelineStageService.createStage(stage);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PipelineStage> updateStage(@PathVariable Integer id, @RequestBody PipelineStage stageDetails) {
        PipelineStage updated = pipelineStageService.updateStage(id, stageDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Integer id) {
        boolean deleted = pipelineStageService.deleteStage(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}