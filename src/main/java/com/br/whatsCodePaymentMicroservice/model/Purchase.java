package com.br.whatsCodePaymentMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

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



    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_client")
    private Client client;

}
