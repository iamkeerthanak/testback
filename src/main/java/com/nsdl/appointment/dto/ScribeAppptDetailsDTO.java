package com.nsdl.appointment.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScribeAppptDetailsDTO {
	
	@NotNull(message = "Appointment ID cannot be null or blank")
	@Valid
	private String appointmentID;

}
