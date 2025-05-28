package org.example.bsaas.controller;

import org.example.bsaas.model.DealContact;
import org.example.bsaas.service.DealContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealcontacts")
public class DealContactController {

    @Autowired
    private DealContactService dealContactService;

    @GetMapping
    public List<DealContact> getAllDealContacts() {
        return dealContactService.getAllDealContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealContact> getDealContactById(@PathVariable Integer id) {
        DealContact dealContact = dealContactService.getDealContactById(id);
        if (dealContact != null) {
            return ResponseEntity.ok(dealContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DealContact> createDealContact(@RequestBody DealContact dealContact) {
        DealContact created = dealContactService.createDealContact(dealContact);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealContact> updateDealContact(@PathVariable Integer id, @RequestBody DealContact dealContactDetails) {
        DealContact updated = dealContactService.updateDealContact(id, dealContactDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealContact(@PathVariable Integer id) {
        boolean deleted = dealContactService.deleteDealContact(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}