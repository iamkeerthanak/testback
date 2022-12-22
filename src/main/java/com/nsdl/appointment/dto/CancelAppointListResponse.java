package com.nsdl.appointment.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CancelAppointListResponse {
	
	private String appointmentId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	private Date appointmentDate;
	private String appointmentSlot;
	private String patientName;

}
