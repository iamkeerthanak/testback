package com.nsdl.appointment.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nsdl.appointment.constants.AppointmentConstants;

public class SlotValidation implements ConstraintValidator<SlotTypeValidator, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {


		return value != null && value.equalsIgnoreCase(AppointmentConstants.TELECONSULTATION)
		          || value.equalsIgnoreCase(AppointmentConstants.IN_CLINIC);
	}

}
