package com.nsdl.appointment.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsdl.appointment.dto.UserDTO;
import com.nsdl.appointment.entity.AppointmentDtlsAuditEntity;
import com.nsdl.appointment.entity.AppointmentDtlsEntity;
import com.nsdl.appointment.repository.AppointmentDtlsAuditRepository;
import com.nsdl.appointment.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AppointmentDtlsAuditRepository auditRepository;

	@Autowired
	private UserDTO userDetails;

	@Override
	public boolean auditAppointmentData(AppointmentDtlsEntity appointmentDtlsEntity) {
		// TODO Auto-generated method stub
		AppointmentDtlsAuditEntity appointmentDtlsAuditEntity = new AppointmentDtlsAuditEntity();
		appointmentDtlsAuditEntity.setAdIdPk(appointmentDtlsEntity.getAdIdPk());
		appointmentDtlsAuditEntity.setAdApptId(appointmentDtlsEntity.getAdApptId());
		appointmentDtlsAuditEntity.setAdDrUserIdFk(appointmentDtlsEntity.getDocMstrDtlsEntity().getDmdUserId());
		appointmentDtlsAuditEntity.setAdPtUserIdFk(appointmentDtlsEntity.getPatientRegDtlsEntity().getPrdUserId());
		appointmentDtlsAuditEntity.setAdApptDateFk(appointmentDtlsEntity.getAdApptDateFk());
		appointmentDtlsAuditEntity.setAdApptSlotFk(appointmentDtlsEntity.getAdApptSlotFk());
		appointmentDtlsAuditEntity.setAdApptBookedFor(appointmentDtlsEntity.getAdApptBookedFor());
		appointmentDtlsAuditEntity.setAdIsbooked(appointmentDtlsEntity.getAdIsbooked());
		appointmentDtlsAuditEntity.setAdPatientDob(appointmentDtlsEntity.getAdPatientDob());
		appointmentDtlsAuditEntity.setAdApptStatus(appointmentDtlsEntity.getAdApptStatus());
		appointmentDtlsAuditEntity.setAdPmtTransIdFk(appointmentDtlsEntity.getPaymentDtlsEntity().getPdPmtTransId());
		appointmentDtlsAuditEntity.setApCancelReason(appointmentDtlsEntity.getApCancelReason());
		appointmentDtlsAuditEntity.setApCancelDate(appointmentDtlsEntity.getApCancelDate());
		appointmentDtlsAuditEntity.setAdCreatedBy(appointmentDtlsEntity.getAdCreatedBy());
		appointmentDtlsAuditEntity.setAdScrbUserId(appointmentDtlsEntity.getAdScrbUserId());
		appointmentDtlsAuditEntity.setAdCreatedTmstmp(appointmentDtlsEntity.getAdCreatedTmstmp());
		appointmentDtlsAuditEntity.setAdModifiedBy(appointmentDtlsEntity.getAdModifiedBy());
		appointmentDtlsAuditEntity.setAdModifiedTmstmp(appointmentDtlsEntity.getAdModifiedTmstmp());
		appointmentDtlsAuditEntity.setAdOptiVersion(appointmentDtlsEntity.getAdOptiVersion());
		appointmentDtlsAuditEntity.setUserId(userDetails.getUserName());
		appointmentDtlsAuditEntity.setTimestamp(LocalDateTime.now());
		auditRepository.save(appointmentDtlsAuditEntity);
		return true;

	}

}
