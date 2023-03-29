package com.br.whatsCodePaymentMicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="payment_value")
    private BigDecimal paymentValue;

    @Column(name="installment_quantity")
    private Integer installmentQuantity;

    @Column(name="purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(mappedBy= "purchase", cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Installment> installment = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "id_client")
    private Client client;

}
