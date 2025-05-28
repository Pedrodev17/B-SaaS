package org.example.bsaas.controller;

import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.service.DealService;
import org.example.bsaas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    @Autowired
    private DealService dealService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Deal> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Integer id) {
        Deal deal = dealService.getDealById(id);
        if (deal != null) {
            return ResponseEntity.ok(deal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
        // Se o ownerUser vier apenas com o ID, busca o User completo
        if (deal.getOwnerUser() != null && deal.getOwnerUser().getUserId() != null) {
            User owner = userService.getUserById(deal.getOwnerUser().getUserId());
            deal.setOwnerUser(owner);
        }
        Deal created = dealService.createDeal(deal);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable Integer id, @RequestBody Deal dealDetails) {
        if (dealDetails.getOwnerUser() != null && dealDetails.getOwnerUser().getUserId() != null) {
            User owner = userService.getUserById(dealDetails.getOwnerUser().getUserId());
            dealDetails.setOwnerUser(owner);
        }
        Deal updated = dealService.updateDeal(id, dealDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Integer id) {
        boolean deleted = dealService.deleteDeal(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}