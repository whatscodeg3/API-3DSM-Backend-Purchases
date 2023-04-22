package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.InstallmentDto;
import com.br.whatsCodePaymentMicroservice.model.Installment;
import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.request.InstallmentRequest;
import com.br.whatsCodePaymentMicroservice.service.InstallmentService;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
            modelInstallment.setInstallmentDueDate(currentDate);
            modelInstallment.setPurchase(purchase);
            currentDate = currentDate.plusDays(30);
            installmentList.add(modelInstallment);
        }

        List<Installment> installments = installmentService.createMany(installmentList);

        for(Installment installment : installments) {
            var installmentId = installment.getId();
            Link selfLink = linkTo(InstallmentController.class).slash(installmentId).withSelfRel();
            installment.add(selfLink);
            Link installmentsLink = linkTo(methodOn(InstallmentController.class).findAll()).withRel("allInstallments");
            installment.add(installmentsLink);

        }

        purchase.setInstallment(installmentList);
        return ResponseEntity.status(HttpStatus.CREATED).body(installmentService.createMany(installments));

    }

    @GetMapping
    public ResponseEntity<List<Installment>> findAll() {
        List<Installment> installments = installmentService.findAll();

        for(Installment installment : installments) {
            var installmentId = installment.getId();
            Link selfLink = linkTo(InstallmentController.class).slash(installmentId).withSelfRel();
            installment.add(selfLink);

        }


        return ResponseEntity.status(HttpStatus.OK).body(installments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Installment> findById(@PathVariable("id") Long id) {
        Optional<Installment> installmentOptional = installmentService.findById(id);

        Installment installment = installmentOptional.orElseThrow(() -> new RuntimeException("Installment not Found"));
        Link installmentsLink = linkTo(methodOn(InstallmentController.class).findAll()).withRel("allInstallments");
        installment.add(installmentsLink);


        return ResponseEntity.status(HttpStatus.OK).body(installment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody InstallmentRequest installmentRequest) {
        Optional<Installment> installmentOptional = installmentService.findById(id);
        if (installmentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Installment not found");
        }
        var currentDate = LocalDate.now();
        var installmentModel = installmentOptional.get();
        installmentModel.setIsInstallmentPayed(true);
        installmentModel.setPaymentDate(LocalDate.parse(installmentRequest.getPaymentDate()));
        installmentModel.setCreditDate(currentDate.plusDays(installmentRequest.getDaysToCredit()));

        return ResponseEntity.status(HttpStatus.OK).body(installmentService.update(installmentModel));
    }
}
