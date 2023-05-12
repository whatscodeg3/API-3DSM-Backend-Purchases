package com.br.whatsCodePaymentMicroservice.controller;

import com.br.whatsCodePaymentMicroservice.model.Purchase;
import com.br.whatsCodePaymentMicroservice.model.mongo.MongoInstallment;
import com.br.whatsCodePaymentMicroservice.repository.PurchaseRepository;
import com.br.whatsCodePaymentMicroservice.service.PurchaseService;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MongoToMysqlController {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/mongo-to-mysql")
    public String mongoToMysql() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            MongoClient mongoClient = MongoClients.create();
            MongoDatabase mongoDatabase = mongoClient.getDatabase("payment_service_db");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("purchases");

            String url = "jdbc:mysql://localhost:3306/payment_service_db";
            String user = "root";
            String password = "";

            try(Connection conn = DriverManager.getConnection(url, user, password)) {
                for (Document doc: mongoCollection.find()) {
                    Long id = doc.getLong("_id");
                    BigDecimal paymentValue =  new BigDecimal(doc.getDouble("payment_value"));
                    Integer installmentQuantity = doc.getInteger("installment_quantity");
                    LocalDate purchaseDate = LocalDate.parse(doc.getString("purchase_data"));
                    String clientCpf = doc.getString("cpf");
                    List<MongoInstallment> installments = doc.getList("installments", MongoInstallment.class);



                    // inserting data in mysql
                    Purchase newPurchase = new Purchase();
                    newPurchase.setPurchaseDate();
                    newPurchase.setClient(purchaseService.searchClient(clientCpf));
                    newPurchase.setInstallment();
                    newPurchase.setPaymentValue();
                    newPurchase.setInstallmentQuantity();
                    purchaseRepository.save(newPurchase);




                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

        });

        return "Asynchronous operation started.";
    }
}
