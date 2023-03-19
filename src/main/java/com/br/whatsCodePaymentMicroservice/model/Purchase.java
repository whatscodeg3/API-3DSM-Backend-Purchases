package com.br.whatsCodePaymentMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name="purchase_date")
    private Date purchaseDate;

    @OneToMany(mappedBy= "purchase")
    private Set<Installment> installment = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_client")
    private Client client;

    // Tenho 1 compra com 12 parcelas OneToMany
    // Tenho 12 parcelas para 1 compra ManyToOne

}
