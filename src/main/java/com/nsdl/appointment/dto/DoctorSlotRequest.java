package com.nsdl.appointment.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DoctorSlotRequest {
	
	@NotNull(message = "doctorId must not be null")
	private String doctorUserId;
	
	@NotNull(message = "date must not be null")
	private Date slotDate;

}
