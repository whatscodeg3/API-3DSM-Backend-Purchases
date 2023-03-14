package com.br.whatsCodePaymentMicroservice.service;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;


    // CRUD

    public Purchase create(Purchase purchase) {
        return purchase;
    }

    public List<Purchase> findAll() {
        return this.purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(Long id) {
        return this.purchaseRepository.findById(id);
    }

    public Purchase update(Long id, Purchase purchase) {
        // Do the verification of the existent entity and pick him by the id
        return this.purchaseRepository.save(purchase);
    }

    public void delete(Long id) {
        this.purchaseRepository.deleteById(id);
    }
}
