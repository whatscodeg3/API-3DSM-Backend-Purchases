package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.dto.PurchaseDto;
import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Employee;
import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    // CRUD

    @PostMapping("/{cpf}")
    @PreAuthorize("hasAnyAuthority('Comercial') or hasAnyAuthority('Administrador')")
    public ResponseEntity<Object> create(@PathVariable("cpf") String cpf, @RequestBody PurchaseDto purchaseDto, @RequestParam("token") String token) {
        var purchaseModel = new Purchase();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Purchase purchaseCreated = purchaseService.create(purchaseModel, cpf, token, employee);

        Link selfLink = linkTo(PurchaseController.class).slash(purchaseCreated.getId()).withSelfRel();
        purchaseCreated.add(selfLink);

        Link relLink = linkTo(methodOn(PurchaseController.class).findAll()).withRel("allPurchases");
        purchaseCreated.add(relLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseCreated);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Comercial') or hasAnyAuthority('Administrador') or hasAnyAuthority('Financeiro')")
    public ResponseEntity<List<Purchase>> findAll() {
        List<Purchase> purchases = purchaseService.findAll();

        for(Purchase purchase : purchases) {
            var purchaseId = purchase.getId();
            Link selfLink = linkTo(PurchaseController.class).slash(purchaseId).withSelfRel();
            purchase.add(selfLink);
        }

        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Comercial') or hasAnyAuthority('Administrador') or hasAnyAuthority('Financeiro')")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);
        Purchase purchase = purchaseOptional.orElseThrow(() -> new RuntimeException("Purchase not Found"));
        Link link = linkTo(methodOn(PurchaseController.class).findAll()).withRel("allPurchases");
        purchase.add(link);

        return ResponseEntity.status(HttpStatus.OK).body(purchaseOptional.get());


    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador') or hasAnyAuthority('Financeiro')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody PurchaseDto purchaseDto) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);
        if (purchaseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        }
        var purchaseModel = new Purchase();
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        purchaseModel.setPurchaseDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.update(id, purchaseModel, employee));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador') or hasAnyAuthority('Financeiro')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Purchase> purchaseOptional = purchaseService.findById(id);
        if(purchaseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        }
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        purchaseService.delete(id, employee.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body("Purchase deleted successfully");
    }



}
