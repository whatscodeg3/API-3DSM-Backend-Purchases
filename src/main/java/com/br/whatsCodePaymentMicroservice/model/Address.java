package com.br.whatsCodePaymentMicroservice.model;

import jakarta.persistence.*;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cep;

    @Column
    private String publicPlace;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String complement;

    @OneToOne(mappedBy = "address")
    private Client client;
}
