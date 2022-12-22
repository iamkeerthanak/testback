package com.nsdl.appointment.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PatientDetailsRequest {
	
	@NotNull(message = "patientId must not be null")
	private String ptRegID;
	

}
