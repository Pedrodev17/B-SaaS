package org.example.bsaas.controller;

import org.example.bsaas.dto.DealCollaboratorRequestDTO;
import org.example.bsaas.dto.DealCollaboratorResponseDTO;
import org.example.bsaas.service.DealCollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deal collaborators")
public class DealCollaboratorController {

    @Autowired
    private DealCollaboratorService dealCollaboratorService;

    @GetMapping
    public List<DealCollaboratorResponseDTO> getAllDealCollaborators() {
        return dealCollaboratorService.getAllDealCollaborators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealCollaboratorResponseDTO> getDealCollaboratorById(@PathVariable Integer id) {
        DealCollaboratorResponseDTO dto = dealCollaboratorService.getDealCollaboratorById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DealCollaboratorResponseDTO> createDealCollaborator(
            @Validated @RequestBody DealCollaboratorRequestDTO request) {
        DealCollaboratorResponseDTO created = dealCollaboratorService.createDealCollaborator(request);
        return ResponseEntity.created(URI.create("/api/dealcollaborators/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealCollaboratorResponseDTO> updateDealCollaborator(
            @PathVariable Integer id, @Validated @RequestBody DealCollaboratorRequestDTO request) {
        DealCollaboratorResponseDTO updated = dealCollaboratorService.updateDealCollaborator(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealCollaborator(@PathVariable Integer id) {
        dealCollaboratorService.deleteDealCollaborator(id);
        return ResponseEntity.noContent().build();
    }
}