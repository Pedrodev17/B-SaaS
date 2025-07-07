package org.example.bsaas.controller;

import org.example.bsaas.dto.DealContactRequestDTO;
import org.example.bsaas.dto.DealContactResponseDTO;
import org.example.bsaas.service.DealContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deal contacts")
public class DealContactController {

    @Autowired
    private DealContactService dealContactService;

    @GetMapping
    public List<DealContactResponseDTO> getAllDealContacts() {
        return dealContactService.getAllDealContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealContactResponseDTO> getDealContactById(@PathVariable Integer id) {
        DealContactResponseDTO dto = dealContactService.getDealContactById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DealContactResponseDTO> createDealContact(
            @Validated @RequestBody DealContactRequestDTO request) {
        DealContactResponseDTO created = dealContactService.createDealContact(request);
        return ResponseEntity.created(URI.create("/api/dealcontacts/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealContactResponseDTO> updateDealContact(
            @PathVariable Integer id, @Validated @RequestBody DealContactRequestDTO request) {
        DealContactResponseDTO updated = dealContactService.updateDealContact(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealContact(@PathVariable Integer id) {
        dealContactService.deleteDealContact(id);
        return ResponseEntity.noContent().build();
    }
}