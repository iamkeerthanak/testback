package com.nsdl.appointment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.PaymentDtlsEntity;

@Repository
@Transactional
public interface PaymentDtlsRepository extends JpaRepository<PaymentDtlsEntity, Long> {

	public PaymentDtlsEntity findByPdPmtTransId(String transactionId);

}
