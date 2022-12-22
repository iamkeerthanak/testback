package com.nsdl.appointment.exception;

import org.springframework.validation.Errors;

public class RequestEntityException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3459201345522737193L;

	private final Errors errors;

	public RequestEntityException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

}
