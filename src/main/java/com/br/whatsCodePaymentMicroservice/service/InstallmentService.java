package com.br.whatsCodePaymentMicroservice.service;


import com.br.whatsCodePaymentMicroservice.model.Installment;
import com.br.whatsCodePaymentMicroservice.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallmentService {


    @Autowired
    InstallmentRepository installmentRepository;

    // CRUD

    public List<Installment> createMany(List<Installment> installments) {
        return installmentRepository.saveAll(installments);
    }

    public List<Installment> findAll() {
        return this.installmentRepository.findAll();
    }

    public Optional<Installment> findById(Long id) {
        return this.installmentRepository.findById(id);
    }

    public Installment update(Long id, Installment installment) {
        // Do the verification of the existent entity and pick him by the id
        installment.setId(id);
        return this.installmentRepository.save(installment);
    }

    public void delete(Long id) {
        this.installmentRepository.deleteById(id);
    }

}


