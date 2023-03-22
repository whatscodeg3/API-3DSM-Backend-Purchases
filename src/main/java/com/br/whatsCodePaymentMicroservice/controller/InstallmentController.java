package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.InstallmentDto;
import com.br.whatsCodePaymentMicroservice.model.Installment;
import com.br.whatsCodePaymentMicroservice.service.InstallmentService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/installments")
public class InstallmentController {

    @Autowired
    InstallmentService installmentService;

    // CRUD

    @PostMapping
    public ResponseEntity<List<Installment>> create(@RequestBody List<Installment> installments, Double quantityInstallment, Double totalValuePurchase) {
        LocalDate currentDate = LocalDate.now();
        var installmentValue = totalValuePurchase/quantityInstallment;
        for(int i = 0; i <= quantityInstallment; i++) {
            var modelInstallment = new Installment();
            modelInstallment.setInstallmentValue(new BigDecimal(installmentValue));
            modelInstallment.setIsInstallmentPayed(false);
            modelInstallment.setPaymentDate(currentDate);
            currentDate = currentDate.plusDays(30);
        }


        for (Installment installment : installments) {
            installment.setPaymentDate(currentDate);
            currentDate = currentDate.plusDays(30);
        }

        /*for(InstallmentDto installmentDto : installmentDtos) {
            List<Installment> installmentModelList = new ArrayList<>();
            var installmentModel = new Installment();
            BeanUtils.copyProperties(installmentDto, installmentModel);
            installmentModelList.add(installmentModel);
        }*/

        /*
        *  List<Installment> savedInstallments = installmentService.createMany(installments);
       List<Object> response = new ArrayList<>();
       response.add("Saved installments:");
       response.addAll(savedInstallments);
       return ResponseEntity.ok(response);*/

        return ResponseEntity.status(HttpStatus.CREATED).body(installmentService.createMany(installments));

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
