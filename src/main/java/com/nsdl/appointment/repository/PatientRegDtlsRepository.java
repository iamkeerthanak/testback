package com.nsdl.appointment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.PatientRegDtlsEntity;

@Repository
@Transactional
public interface PatientRegDtlsRepository extends JpaRepository<PatientRegDtlsEntity, Long>{
	
	public PatientRegDtlsEntity findByPrdUserId(String patientId);

}
