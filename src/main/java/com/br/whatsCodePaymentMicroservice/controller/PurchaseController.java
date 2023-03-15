package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.PurchaseDto;
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
    public ResponseEntity<Object>create(@RequestBody PurchaseDto purchaseDto) {
        var purchaseModel = new Purchase();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        purchaseModel.setPurchaseDate(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(purchaseModel));
    }

    @GetMapping
    public List<Purchase> findAll() {
        return purchaseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Purchase> findById(@PathVariable("id") Long id) {
        return purchaseService.findById(id);
    }

    @PatchMapping("/{id}")
    public Purchase update(@PathVariable("id") Long id, @RequestBody Purchase purchase) {
        return purchase;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.purchaseService.delete(id);
    }



}
