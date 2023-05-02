package com.br.whatsCodePaymentMicroservice.repository.mongo;

import com.br.whatsCodePaymentMicroservice.model.mongo.MongoInstallment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoInstallmentRepository extends MongoRepository<MongoInstallment, String> {
}
