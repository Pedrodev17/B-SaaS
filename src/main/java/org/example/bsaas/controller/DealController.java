package org.example.bsaas.controller;

import org.example.bsaas.dto.DealRequestDTO;
import org.example.bsaas.dto.DealResponseDTO;
import org.example.bsaas.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping
    public List<DealResponseDTO> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponseDTO> getDealById(@PathVariable Integer id) {
        DealResponseDTO dto = dealService.getDealById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DealResponseDTO> createDeal(
            @Validated @RequestBody DealRequestDTO request) {
        DealResponseDTO created = dealService.createDeal(request);
        return ResponseEntity.created(URI.create("/api/deals/" + created.getDealId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponseDTO> updateDeal(
            @PathVariable Integer id, @Validated @RequestBody DealRequestDTO request) {
        DealResponseDTO updated = dealService.updateDeal(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Integer id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}