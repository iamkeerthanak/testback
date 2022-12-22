package com.nsdl.appointment.service;

import com.nsdl.appointment.entity.AppointmentDtlsEntity;

public interface AuditService {
	
	public boolean auditAppointmentData(AppointmentDtlsEntity appointmentDtlsEntity);

}
