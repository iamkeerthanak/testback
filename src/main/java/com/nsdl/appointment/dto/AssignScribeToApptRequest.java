package com.nsdl.appointment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AssignScribeToApptRequest {

	private String drRegID;
	private Date assignDate;
	private String scribeID;

}
