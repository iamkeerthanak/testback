package com.nsdl.appointment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CancelAppointFilterRequest {
	
	private String appointmentId;
	private Date fromDate;
	private Date toDate;

}
