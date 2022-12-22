package com.nsdl.appointment.exception;

public class DataException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
