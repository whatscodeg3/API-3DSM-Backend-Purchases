package com.br.whatsCodePaymentMicroservice.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Document(collection="purchases")
@Data
public class MongoPurchase {
    @Id
    private String id;

    private BigDecimal paymentValue;

    private Integer installmentQuantity;

    private LocalDate purchaseDate;

    private String cpf;

    @DBRef
    private List<MongoInstallment> installments;

}
