package com.nsdl.appointment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {
	
	//@NotNull(message = "slot must not be null")
	private String appointmentSlot;
	
	//@NotNull(message = "date must not be null")
	private Date appointmentDate;
	
	private String docUserID;
	
	private String ptUserID;
	
	private String bookedFor;
	
	private String appointmentType;

}
