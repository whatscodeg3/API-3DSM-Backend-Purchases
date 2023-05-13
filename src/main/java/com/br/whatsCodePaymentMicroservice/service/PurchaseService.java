package com.br.whatsCodePaymentMicroservice.service;

import com.br.whatsCodePaymentMicroservice.model.AuditingLog;
import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Employee;
import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.repository.AuditingLogRepository;
import com.br.whatsCodePaymentMicroservice.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    AuditingLogRepository auditingLogRepository;


    // CRUD

    @Transactional
    public Purchase create(Purchase purchase, String cpf, String token, Employee employee) {

        purchase.setClient(searchClient(cpf, token));
        purchase.setCreatedBy(employee.getEmail());
        purchase.setCreatedAt(new Date());

        return purchaseRepository.save(purchase);

    }

    public List<Purchase> findAll() {

        return this.purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(Long id) {

        return this.purchaseRepository.findById(id);
    }

    public Purchase update(Long id, Purchase purchase, Employee employee) {
        purchase.setId(id);
        purchase.setUpdatedBy(employee.getEmail());
        purchase.setUpdatedAt(new Date());
        return this.purchaseRepository.save(purchase);
    }

    public void delete(Long id, String employeeName) {
        AuditingLog auditing = new AuditingLog();

        auditing.setDeletedBy(employeeName);
        auditing.setDeletedAt(new Date());
        auditing.setEntityType("Purchase");
        auditing.setEntityId(id);

        auditingLogRepository.save(auditing);

        this.purchaseRepository.deleteById(id);

    }

    public Client searchClient(String cpf, String token){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);


        ResponseEntity<Client> client = restTemplate.exchange("http://localhost:8080/client/queryFromCpf/"+cpf, HttpMethod.GET,new HttpEntity<>(headers), Client.class);
        return client.getBody();
    }
}
