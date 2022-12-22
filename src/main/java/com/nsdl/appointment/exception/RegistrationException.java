package com.nsdl.appointment.exception;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegistrationException extends BaseException {

	private static final long serialVersionUID = -4142059214431390929L;
	
	public RegistrationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
	
	public RegistrationException(List<ServiceError> serviceError) {
		super(serviceError);
	}

	public RegistrationException(List<ServiceError> serviceError, Throwable rootCause) {
		super(serviceError, rootCause);
	}

	
}
