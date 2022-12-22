package com.nsdl.appointment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.PatientRevDtlsEntity;

@Repository
@Transactional
public interface PatientRevDtlsRepository extends JpaRepository<PatientRevDtlsEntity, Long>{

}
