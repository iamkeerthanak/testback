package com.nsdl.appointment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApptDtlsDto {

	private String dateTime;

	private String appointmentId;

	private String bookedFor;

	private String drName;

	private String specialization;

	public ApptDtlsDto(String appointmentId, String dateTime, String bookedFor, String drName, String specialization) {
		super();
		this.appointmentId = appointmentId;
		this.dateTime = dateTime;
		this.bookedFor = bookedFor;
		this.drName = drName;
		this.specialization = specialization;
	}

}
