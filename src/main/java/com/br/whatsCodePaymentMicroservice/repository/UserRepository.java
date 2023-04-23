package com.br.whatsCodePaymentMicroservice.repository;

import com.br.whatsCodePaymentMicroservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
