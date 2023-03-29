package com.br.whatsCodePaymentMicroservice.service;

import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;


    // CRUD

    @Transactional
    public Purchase create(Purchase purchase, String cpf) {

        purchase.setClient(searchClient(cpf));

        return purchaseRepository.save(purchase);

    }

    public List<Purchase> findAll() {

        return this.purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(Long id) {

        return this.purchaseRepository.findById(id);
    }

    public Purchase update(Long id, Purchase purchase) {
        purchase.setId(id);
        return this.purchaseRepository.save(purchase);
    }

    public void delete(Long id) {
        this.purchaseRepository.deleteById(id);
    }

    public Client searchClient(String cpf){

        RestTemplate restTemplate = new RestTemplate();

        Client client = restTemplate.getForObject("http://localhost:8080/client/queryFromCpf/"+cpf, Client.class);

        return client;
    }
}
