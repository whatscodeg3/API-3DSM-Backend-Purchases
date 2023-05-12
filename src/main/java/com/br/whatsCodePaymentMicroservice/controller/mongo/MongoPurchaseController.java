package com.br.whatsCodePaymentMicroservice.controller.mongo;

import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.mongo.MongoPurchase;
import com.br.whatsCodePaymentMicroservice.repository.mongo.MongoPurchaseRepository;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo/purchases")
public class MongoPurchaseController {

    @Autowired
    private MongoPurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<MongoPurchase> createPurchase(@PathVariable("cpf")  String cpf,
                                                        @RequestBody MongoPurchase purchase) {
        Client client = purchaseService.searchClient(cpf);

        purchase.setCpf(client.getCpf());
        MongoPurchase newPurchase = purchaseRepository.save(purchase);

        return ResponseEntity.ok(newPurchase);

    }

    @GetMapping
    public ResponseEntity<List<MongoPurchase>> findAll() {
        List<MongoPurchase> purchases = purchaseRepository.findAll();
        return ResponseEntity.ok(purchases);
    }
}
