package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.model.Installment;
import com.br.whatsCodePaymentMicroservice.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/installments")
public class InstallmentController {

    @Autowired
    InstallmentService installmentService;

    // CRUD

    @PostMapping
    public Installment create (@RequestBody Installment installment) {
        return installmentService.create(installment);
    }

    @GetMapping
    public List<Installment> findAll() {
        return installmentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Installment> findById(@PathVariable("id") Long id) {
        return installmentService.findById(id);
    }
}
