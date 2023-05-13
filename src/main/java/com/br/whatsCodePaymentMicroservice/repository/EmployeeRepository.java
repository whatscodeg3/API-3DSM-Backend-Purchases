package com.br.whatsCodePaymentMicroservice.repository;

import com.br.whatsCodePaymentMicroservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByCpf(String cpf);
}