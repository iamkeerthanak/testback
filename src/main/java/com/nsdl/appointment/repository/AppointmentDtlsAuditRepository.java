package com.nsdl.appointment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.AppointmentDtlsAuditEntity;

@Repository
@Transactional
public interface AppointmentDtlsAuditRepository extends JpaRepository<AppointmentDtlsAuditEntity, Long> {

}
