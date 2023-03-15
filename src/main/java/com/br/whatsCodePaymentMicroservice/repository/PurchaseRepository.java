package com.br.whatsCodePaymentMicroservice.repository;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT Client.fullName from Client WHERE fullName=:fullname")
    Purchase findByName(@Param("fullName")String fullName);

}
