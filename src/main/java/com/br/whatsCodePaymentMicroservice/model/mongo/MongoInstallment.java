package com.br.whatsCodePaymentMicroservice.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "installments")
@Data
public class MongoInstallment {

    @Id
    private String id;

    private LocalDate installmentDueDate;

    private BigDecimal installmentValue;

    private Boolean isInstallmentPayed;

    private LocalDate paymentDate;

    private LocalDate creditDate;


    private MongoPurchase purchase;

}
