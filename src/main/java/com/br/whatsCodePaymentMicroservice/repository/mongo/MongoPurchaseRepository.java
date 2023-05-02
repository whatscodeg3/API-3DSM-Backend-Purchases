package com.br.whatsCodePaymentMicroservice.repository.mongo;

import com.br.whatsCodePaymentMicroservice.model.mongo.MongoPurchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoPurchaseRepository extends MongoRepository<MongoPurchase, String> {
}
