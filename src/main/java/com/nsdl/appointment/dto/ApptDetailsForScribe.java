package com.nsdl.appointment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApptDetailsForScribe {
	
	private String appointmentID;
	private String appointmentTime;
	private String patientName;
	private String patientRegId;
	private String drRegID;
	private String apptDate;
	private String status;
	private String doctorName;
	private String docProfilePhoto;
	private String ptEmail;
	private String ptMNo;
	
	

}
