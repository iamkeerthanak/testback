package com.nsdl.appointment.dto;

import lombok.Data;

@Data
public class UsrOtpEmailVerifyDTO {
	
	   private String userId;
	   private String ptEmailId;
	   private String templateType;
	   private Long  docMobileNo;
	   private String  ptMobileNo;
	   private String sendType;
	   private String doctorName;
	   private String docEmailId;
	   private String appointmentTime;
	   private String appointmentDate;
	   private String patientName;
	   private String preAssessmentLink;
	   private boolean preAssessmentFlag;

}
