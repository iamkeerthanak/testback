package com.nsdl.appointment.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class AssignedScribeToApptResponse {

	private String scribeID;
	private Map<String, List<ApptDetailsForScribe>> scribeDetails;

}
