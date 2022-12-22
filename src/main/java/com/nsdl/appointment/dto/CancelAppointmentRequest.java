package com.nsdl.appointment.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CancelAppointmentRequest {

	@NotNull(message = "AppointmentId cannot be null or blank")
	@Valid
	private String apptId;

	/*@NotNull(message = "Reason cannot be null or blank")
	@Valid
	private String reason;*/
}
