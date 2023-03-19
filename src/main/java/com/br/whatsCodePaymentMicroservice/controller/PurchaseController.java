package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.PurchaseDto;
import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    // CRUD

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PurchaseDto purchaseDto) {
        var purchaseModel = new Purchase();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        purchaseModel.setPurchaseDate(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(purchaseModel));
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);

        return purchaseOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(purchaseOptional.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody PurchaseDto purchaseDto) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);
        if (purchaseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        }
        var purchaseModel = new Purchase();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        purchaseModel.setPurchaseDate(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.create(purchaseModel));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);
        if(purchaseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        }
        purchaseService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Purchase deleted successfully");
    }



}
