package com.nsdl.appointment.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = SlotValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SlotTypeValidator {
	
	String message() default "Slot Type or Consultation Type is Null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
