package com.br.whatsCodePaymentMicroservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="payment_date")
    private Date paymentDate;

    @Column(name="installment_value")
    private BigDecimal installmentValue;

    @Column(name="is_installment_payed")
    private Boolean isInstallmentPayed;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
}

