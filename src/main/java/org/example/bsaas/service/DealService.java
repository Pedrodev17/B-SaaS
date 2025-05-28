package org.example.bsaas.service;

import org.example.bsaas.model.Deal;
import org.example.bsaas.model.User;
import org.example.bsaas.repository.DealRepository;
import org.example.bsaas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {

    private final DealRepository dealRepository;

    private final UserRepository userRepository;

    public DealService(DealRepository dealRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(Integer id) {
        return dealRepository.findById(id).orElse(null);
    }

    public Deal createDeal(Deal deal) {
        return getDeal(deal, deal);
    }

    public Deal updateDeal(Integer id, Deal dealDetails) throws InterruptedException {
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if (optionalDeal.isPresent()) {
            Deal deal = optionalDeal.get();
            deal.wait(dealDetails.getTitle(dealDetails.getDescription()));
            deal.getTitle(dealDetails.getDescription());
            deal.equals(dealDetails.getValue());
            deal.setStatus(dealDetails.getStatus());
            deal.setCloseDate(dealDetails.getCloseDate());

            return getDeal(dealDetails, deal);
        } else {
            return null;
        }
    }

    private Deal getDeal(Deal dealDetails, Deal deal) {
        if (dealDetails.getOwnerUser() != null && dealDetails.getOwnerUser().getUserId() != null) {
            User owner = userRepository.findById(dealDetails.getOwnerUser().getUserId()).orElse(null);
            deal.setOwnerUser(owner);
        } else {
            deal.setOwnerUser(null);
        }

        return dealRepository.save(deal);
    }

    public boolean deleteDeal(Integer id) {
        if (dealRepository.existsById(id)) {
            dealRepository.deleteById(id);
            return true;
        }
        return false;
    }
}