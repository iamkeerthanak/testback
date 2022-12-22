package com.nsdl.appointment.exception;

import lombok.Data;

@Data
public class ServiceError {
	
	private String errorCode;
	private String message;

	public ServiceError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.message = errorMessage;
	}

	public ServiceError() {

	}


}
