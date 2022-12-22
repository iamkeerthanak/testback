package com.nsdl.appointment.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.nsdl.appointment.utils.SlotTypeValidator;

import lombok.Data;

@Data
public class SaveAppDetailsRequest {
	
	@NotNull(message = "doctorId must not be null")
	@Valid
	private String drRegID;
	
	private String ptRegID;
	
	@NotNull(message = "transactionId must not be null")
	@Valid
	private String transactionID;
	
	@NotNull(message = "bookForSomeoneElse flag must not be null")
	@Valid
	private String bookForSomeoneElse;
	
	@NotNull(message = "appointmentDetails must not be null")
	@Valid
	private AppointmentDetails appointmentDetails;
	
	@NotNull(message = "patientName must not be null")
	@Valid
	private String patientName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date patientDOB;
	
//	@NotNull(message = "patientName must not be null")
//	@Valid
	private String patientEmail;
	
	@NotNull(message = "patientMNO must not be null")
	@Valid
	private String patientMNO;

	@SlotTypeValidator
	private String consultType;
	
	private String symptomsDetails;
	
	private boolean tncFlag;
}
