package com.br.whatsCodePaymentMicroservice.dto;

import com.br.whatsCodePaymentMicroservice.model.Client;
import com.br.whatsCodePaymentMicroservice.model.Installment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class PurchaseDto {

    private BigDecimal paymentValue;
    private Date purchaseDate;

    private Integer installmentQuantity;
    private List<Installment> installment = new ArrayList<>();
    private Client client;

}
