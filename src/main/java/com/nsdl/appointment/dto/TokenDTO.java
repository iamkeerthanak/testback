package com.nsdl.appointment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TokenDTO {
	
	//private String token;
	
	//private String apptDate;
	
	private String username;
	
	//@NotNull(message = "startDate must not be null")
	private Date availabilityStartDate;

	//@NotNull(message = "endDate must not be null")
	private Date availabilityEndDate;
	

}
