package com.br.whatsCodePaymentMicroservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "installment")
public class Installment extends RepresentationModel<Installment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="installment_due_date")
    private LocalDate installmentDueDate;

    @Column(name="installment_value")
    private BigDecimal installmentValue;

    @Column(name="is_installment_payed")
    private Boolean isInstallmentPayed;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    @Column(name="credit_date")
    private LocalDate creditDate;

    @ManyToOne()
    @JoinColumn(name = "purchase_id")
    @JsonIgnore
    private Purchase purchase;

    @Column
    private String createdBy;

    @Column
    private Date createdAt;

    @Column
    private String updatedBy;

    @Column
    private Date updatedAt;
}

