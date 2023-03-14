package com.br.whatsCodePaymentMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_client")
    private Long id;

    @Column
    private String fullName;
    @Column
    private String cpf;

    @Column
    private String email;

    @Column
    private String telephone;

    @Column
    private LocalDate birthDate;

    @Column
    private Date dateRegister;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy= "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Purchase purchase;


}
