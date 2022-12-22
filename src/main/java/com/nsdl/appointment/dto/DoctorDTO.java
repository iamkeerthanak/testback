package com.nsdl.appointment.dto;

import java.util.List;

import lombok.Data;

@Data
public class DoctorDTO {

	private String doctorId;
	private String doctorName;
	private String speciality;
	private int consultFee;
	private Integer convenienceCharge;
	private String availability;
	private List<SlotDetails> slotDetails;
	private PatientReview patientReview;
	private String drProfilePhoto;
	private String drProfileLink;
}
