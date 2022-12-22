package com.nsdl.appointment.exception;

import java.util.ArrayList;
import java.util.List;


public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -875003872780128394L;
	public static final String EMPTY_SPACE = " ";
	private List<ServiceError> serviceErrors = new ArrayList<>();

	/**
	 * Constructs a new unchecked exception
	 */
	public BaseException() {
		super();
	}

	/**
	 * Constructs a new checked exception with errorMessage
	 * 
	 * @param errorMessage the detail message.
	 */
	public BaseException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructs a new unchecked exception with the specified detail message and
	 * error code.
	 *
	 * @param errorMessage the detail message.
	 * @param errorCode    the error code.
	 * 
	 */
	public BaseException(String errorCode, String errorMessage) {
		super(errorCode + " --> " + errorMessage);
		addServiceError(errorCode, errorMessage);
	}

	/**
	 * Constructs a new unchecked exception with the specified detail message and
	 * error code and error cause.
	 *
	 * 
	 * @param errorCode    the error code
	 * @param errorMessage the detail message.
	 * @param rootCause    the specified cause
	 */
	public BaseException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode + " --> " + errorMessage, rootCause);
		addServiceError(errorCode, errorMessage);
		if (rootCause instanceof BaseException) {
			BaseException exception = (BaseException) rootCause;
			serviceErrors.addAll(exception.serviceErrors);
		}
	}

	public BaseException(List<ServiceError> serviceErrors) {		
		this.serviceErrors = serviceErrors;
	}

	public BaseException(List<ServiceError> serviceError, Throwable rootCause) {
		
		if (rootCause instanceof BaseException) {
			BaseException exception = (BaseException) rootCause;
			serviceErrors.addAll(exception.serviceErrors);
		}
	}

	/*
	 * Returns a String object that can be used to get the exception message.
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	

	/**
	 * This method add the information of error code and error message.
	 * 
	 * @param errorCode the error code
	 * @param errorText the detail message.
	 * @return the instance of current BaseCheckedException
	 */
	public BaseException addServiceError(String errorCode, String errorText) {
		this.serviceErrors.add(new ServiceError(errorCode, errorText));
		return this;
	}
	
	public BaseException addServiceError(List<ServiceError> serviceError) {
		this.serviceErrors.addAll(serviceError);
		return this;
	}

	/**
	 * Returns the list of error codes.
	 * 
	 * @return the list of error codes
	 */
	public List<String> getCodes() {
		List<String> codes = new ArrayList<>();

		for (int i = this.serviceErrors.size() - 1; i >= 0; i--) {
			codes.add(this.serviceErrors.get(i).getErrorCode());
		}

		return codes;
	}

	/**
	 * Returns the list of exception messages.
	 * 
	 * @return the list of exception messages
	 */
	public List<String> getErrorMessages() {
		List<String> errorMessages = new ArrayList<>();

		for (int i = this.serviceErrors.size() - 1; i >= 0; i--) {
			errorMessages.add(this.serviceErrors.get(i).getMessage());
		}

		return errorMessages;
	}

	/**
	 * Return the last error code.
	 * 
	 * @return the last error code
	 */
	public String getErrorCode() {
		return serviceErrors.get(0).getErrorCode();
	}

	/**
	 * Return the last exception message.
	 * 
	 * @return the last exception message
	 */
	public String getErrorMessage() {
		return serviceErrors.get(0).getMessage();
	}
	
	public List<ServiceError> getList() {
		return serviceErrors;
	}

}
