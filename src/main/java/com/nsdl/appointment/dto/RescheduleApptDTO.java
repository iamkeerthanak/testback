package com.nsdl.appointment.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RescheduleApptDTO {
	
	/*@NotNull(message = "doctorId must not be null")
	private String drRegID;
	
	@NotNull(message = "patientId must not be null")
	private String ptRegID;*/
	
	@NotNull(message="appID can not be null")
	@Valid
	private String appID;
	
	private ReschduleApptDetails appointmentDetails;
	
	//private String consultType;

}
