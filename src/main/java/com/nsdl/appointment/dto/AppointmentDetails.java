package com.nsdl.appointment.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AppointmentDetails {
	
	@NotNull(message = "slot must not be null")
	private String appointmentSlot;
	
	@NotNull(message = "date must not be null")
	private Date appointmentDate;

}
