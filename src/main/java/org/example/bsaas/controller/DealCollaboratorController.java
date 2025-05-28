package org.example.bsaas.controller;

import org.example.bsaas.model.DealCollaborator;
import org.example.bsaas.service.DealCollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealcollaborators")
public class DealCollaboratorController {

    @Autowired
    private DealCollaboratorService dealCollaboratorService;

    @GetMapping
    public List<DealCollaborator> getAllDealCollaborators() {
        return dealCollaboratorService.getAllDealCollaborators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealCollaborator> getDealCollaboratorById(@PathVariable Integer id) {
        DealCollaborator dealCollaborator = dealCollaboratorService.getDealCollaboratorById(id);
        if (dealCollaborator != null) {
            return ResponseEntity.ok(dealCollaborator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DealCollaborator> createDealCollaborator(@RequestBody DealCollaborator dealCollaborator) {
        DealCollaborator created = dealCollaboratorService.createDealCollaborator(dealCollaborator);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealCollaborator> updateDealCollaborator(@PathVariable Integer id, @RequestBody DealCollaborator dealCollaboratorDetails) {
        DealCollaborator updated = dealCollaboratorService.updateDealCollaborator(id, dealCollaboratorDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealCollaborator(@PathVariable Integer id) {
        boolean deleted = dealCollaboratorService.deleteDealCollaborator(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}