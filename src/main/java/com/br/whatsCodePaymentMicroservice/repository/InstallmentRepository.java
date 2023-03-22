package com.br.whatsCodePaymentMicroservice.repository;


import com.br.whatsCodePaymentMicroservice.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
