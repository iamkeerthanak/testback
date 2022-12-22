package com.nsdl.appointment.dto;

import lombok.Data;

@Data
public class TemplateDtls {
	private String userId;
	private String mobileNo;
	private String emailId;
	private String sendType;
	private String templateType;
	private String rejectReason;
	private String password;
	private String amount;
	private String patientName;
	private String doctorName;
	private String appointmentTime;
	private String appointmentDate;
	private String docEmailId;
	private String ptEmailId;
	private String diseaseName;
	private String expireTime;
	private String docMobileNo;
	private String ptMobileNo;
	
	private String templateName;
	private String templateContent;
	private String subjectLine;
	private String attachmentPath;
	

}
