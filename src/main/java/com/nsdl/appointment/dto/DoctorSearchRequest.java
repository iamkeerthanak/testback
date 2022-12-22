package com.nsdl.appointment.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DoctorSearchRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String doctorName;
	//@NotNull(message = "specialization must not be null")
	private String speciality;
	private int[] consultFee;
	private String gender;

	//@NotNull(message = "startDate must not be null")
	private Date availabilityStartDate;

	//@NotNull(message = "endDate must not be null")
	private Date availabilityEndDate;
	
	private String doctorId;
	
	private boolean closeDrGrp;
	

}
