package com.br.whatsCodePaymentMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_purchase")
    private Long id;

    @Column(name="payment_value")
    private Double paymentValue;

    @Column(name="purchase_date")
    private Date purchaseDate;

    @ManyToMany
    @JoinTable(name = "purchase_installment", joinColumns = {@JoinColumn(name= "id_purchase")}, inverseJoinColumns =
            {@JoinColumn(name = "id_installment")})
    private Set<Installment> installment = new HashSet<>();


    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

}
