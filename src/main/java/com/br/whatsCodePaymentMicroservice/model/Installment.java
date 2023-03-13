package com.br.whatsCodePaymentMicroservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Installment")
    private Long id;

    @ManyToMany(mappedBy = "installment")
    private Set<Purchase> purchases = new HashSet<>();



}

