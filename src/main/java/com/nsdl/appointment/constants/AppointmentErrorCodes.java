package com.nsdl.appointment.constants;

public enum AppointmentErrorCodes {
	
	INTERNAL_SERVER_ERROR("REG-EXP-001", "Something went wrong, try again later"),
	
	SPECIALIZATION_FIELD_EMPTY("REG-APP-001", "specialization field is empty"),
	SLOT_DETAILS_NOT_AVAILABLE("REG-APP-002", "slot details not found"),
	PATIENT_DETAILS_NOT_AVAILABLE("REG-APP-003", "patient details not found"),
	DOCTORS_NOT_AVAILABLE("REG-APP-004", "doctors are not available for this category"),
	PAYMENT_DETAILS_NOT_PRESENT("REG-APP-005", "payment details not present for transactionId"),
	DOCTOR_DETAILS_NOT_PRESENT("REG-APP-006", "doctor details not found"),
	PAST_DATE_TIME_CANNOT_BOOKED("REG-APP-007", "past date time can't be booked"),
	PAST_DATE_TIME_CANNOT_CANCELLED("REG-APP-008", "past date time can't be cancelled"),
	NO_APPOINTMENT_REGISTERED_FOR_CURRENT_ID("REG-APP-009", "No appointment booked for user with current appointment id"),
	NO_APPOINTMENT_FOUND_FOR_DOCTOR_ID("REG-APP-009", "No appointment found for given doctor id and date"),
	NO_APPOINTMENT_FOUND_FOR_SCRIBE_ID("REG-APP-009", "No appointment found for given scribe id"),
	MISSING_REQUEST_PARAMETER("REG-APP-020", ""),
	PAST_DATE_TIME_RESCHDULED_BOOKED("REG-APP-010", "Sorry, you can not take past date and time to reschedule appointment"),
	SCRIBE_DETAILS_NOT_PRESENT("REG-APP-011", "Scribe details not found"),

	BAD_REQUEST("REG-APP-013", ""),
	REG_EXCEP("REG-APP-014", "Something went wrong, please contact admin"),
	NO_APPOINTMENT_FOUND("REG-APP-013", "No appointment details found for given Appointment ID "),
	DOCTOR_SCRIBE_APPOINTMENT_PRESENT("REG-APP-012", "Doctor Scribe appointment already available"),
	NO_APPOINTMENT_FOUND_FOR_PATIENT_ID("REG-APP-013", "No appointment found for given patient id"),
	INVALID_PATIENT_ID("REG-APP-014" ,"Invalid patiet id"),
	NO_SLOT_AVAILABLE("REG-APP-015","No slot available"),
	USER_AUTHORIZATION_FAILED("REG-APP-016","Unauthorized loggedIn user"),
	NO_CANCEL_APPOINTMENT_FOUND("REG-APP-017","No cancel appointments found");

	private final String errorCode;

	private final String errorMessage;

	private AppointmentErrorCodes(final String errorCode, final String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}


}
