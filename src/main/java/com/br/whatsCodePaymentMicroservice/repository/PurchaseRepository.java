package com.br.whatsCodePaymentMicroservice.repository;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    /*@Query("SELECT Client.fullName from Client WHERE fullName=:fullname")
    Purchase findByName(@Param("fullName")String fullName);*/

    @Query("SELECT c.cpf, c.fullName, p.purchaseDate, p.paymentValue, i.installmentDueDate, i.paymentDate, i.creditDate, i.installmentValue "
            + "FROM Purchase p "
            + "INNER JOIN Client c ON p.client.id = c.id "
            + "INNER JOIN Installment i ON p.id = i.purchase.id "
            + "WHERE i.installmentDueDate BETWEEN :startDate AND :endDate "
            + "GROUP BY p.id")
    List<Object[]> findReportByDueDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c.cpf, c.fullName, p.purchaseDate, p.paymentValue, i.installmentDueDate, i.paymentDate, i.creditDate, i.installmentValue "
            + "FROM Purchase p "
            + "INNER JOIN Client c ON p.client.id = c.id "
            + "INNER JOIN Installment i ON p.id = i.purchase.id "
            + "WHERE i.paymentDate BETWEEN :startDate AND :endDate "
            + "GROUP BY p.id")
    List<Object[]> findReportByPaymentDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c.cpf, c.fullName, p.purchaseDate, p.paymentValue, i.installmentDueDate, i.paymentDate, i.creditDate, i.installmentValue "
            + "FROM Purchase p "
            + "INNER JOIN Client c ON p.client.id = c.id "
            + "INNER JOIN Installment i ON p.id = i.purchase.id "
            + "WHERE i.creditDate BETWEEN :startDate AND :endDate "
            + "GROUP BY p.id")
    List<Object[]> findReportByCreditDate(LocalDate startDate, LocalDate endDate);




}
