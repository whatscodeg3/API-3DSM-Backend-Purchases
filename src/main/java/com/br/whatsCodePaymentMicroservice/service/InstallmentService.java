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

    public Installment create(Installment installment) {
        return installment;
    }

    public List<Installment> findAll() {
        return this.installmentRepository.findAll();
    }

    public Optional<Installment> findById(Long id) {
        return this.installmentRepository.findById(id);
    }

}


