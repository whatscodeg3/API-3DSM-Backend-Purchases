package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.InstallmentDto;
import com.br.whatsCodePaymentMicroservice.model.Installment;
import com.br.whatsCodePaymentMicroservice.service.InstallmentService;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/installments")
public class InstallmentController {

    @Autowired
    InstallmentService installmentService;

    @Autowired
    PurchaseService purchaseService;

    // CRUD

    @PostMapping
    public ResponseEntity<List<Installment>> create(@RequestBody InstallmentDto installmentDto) {
        LocalDate currentDate = LocalDate.now().plusDays(30);
        var totalValuePurchase = installmentDto.getPurchaseValue();
        var quantityInstallment = installmentDto.getInstallmentQuantity();
        var purchaseOptional = purchaseService.findById(installmentDto.getPurchaseId());
        var purchase = purchaseOptional.orElseThrow(() -> new IllegalStateException("Value is absent"));
        var installmentValue = totalValuePurchase.divide(new BigDecimal(quantityInstallment), 2, RoundingMode.HALF_UP);
        List<Installment> installmentList = new ArrayList<>();

        for(int i = 0; i < quantityInstallment; i++) {
            var modelInstallment = new Installment();
            modelInstallment.setInstallmentValue(installmentValue);
            modelInstallment.setIsInstallmentPayed(false);
            modelInstallment.setPaymentDate(currentDate);
            modelInstallment.setPurchase(purchase);
            currentDate = currentDate.plusDays(30);
            installmentList.add(modelInstallment);
        }

        purchase.setInstallment(installmentList);
        return ResponseEntity.status(HttpStatus.CREATED).body(installmentService.createMany(installmentList));

    }

    @GetMapping
    public List<Installment> findAll() {

        return installmentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Installment> findById(@PathVariable("id") Long id) {

        return installmentService.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id) {
        Optional<Installment> installmentOptional = installmentService.findById(id);
        if (installmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Installment not found");
        }
        var installmentModel = installmentOptional.get();
        installmentModel.setIsInstallmentPayed(true);

        return ResponseEntity.status(HttpStatus.OK).body(installmentService.update(installmentModel));
    }
}
