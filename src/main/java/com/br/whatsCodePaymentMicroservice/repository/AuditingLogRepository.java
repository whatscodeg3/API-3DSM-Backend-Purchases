package com.br.whatsCodePaymentMicroservice.repository;

import com.br.whatsCodePaymentMicroservice.model.AuditingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditingLogRepository extends JpaRepository<AuditingLog, Long> {
}
