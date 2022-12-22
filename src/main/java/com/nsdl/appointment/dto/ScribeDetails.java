package com.nsdl.appointment.dto;

import java.util.List;

import lombok.Data;

@Data
public class ScribeDetails {
	
	private String apptDate;
	private List<ApptDetailsForScribe> apptDetails;

}
