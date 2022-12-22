package com.nsdl.appointment.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nsdl.appointment.constants.AppointmentConstants;
import com.nsdl.appointment.constants.AppointmentErrorCodes;
import com.nsdl.appointment.dto.MainResponseDTO;
import com.nsdl.appointment.utils.EmptyCheckUtils;

@RestControllerAdvice
public class ExceptionControllerAdvisor {

	@Autowired
	private ObjectMapper objectMapper;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MainResponseDTO<ServiceError>> validationException(HttpServletRequest httpServletRequest,
			final HttpMessageNotReadableException exception) throws IOException {
		MainResponseDTO<ServiceError> errorResponse = setErrors(httpServletRequest);
		ServiceError serviceErrors = new ServiceError(AppointmentConstants.ERROR, exception.getRootCause().getMessage());
		errorResponse.getErrors().add(serviceErrors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<MainResponseDTO<ServiceError>> validationException(HttpServletRequest httpServletRequest,
			final MissingServletRequestParameterException exception) throws IOException {
		MainResponseDTO<ServiceError> errorResponse = setErrors(httpServletRequest);
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(AppointmentErrorCodes.MISSING_REQUEST_PARAMETER.getErrorCode());
		serviceError.setMessage(exception.getMessage());
		errorResponse.getErrors().add(serviceError);
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(RequestEntityException.class)
	public ResponseEntity<MainResponseDTO<ServiceError>> validationException(HttpServletRequest httpServletRequest,
			final RequestEntityException reqException) throws IOException {
		// ExceptionUtils.logRootCause(reqException);
		MainResponseDTO<ServiceError> errorResponse = setErrors(httpServletRequest);
		if (reqException.getErrors() != null && reqException.getErrors().hasErrors()) {
			errorResponse.getErrors().addAll(getListOfFieldErrors(reqException.getErrors()));
		}
		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}

	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<MainResponseDTO<ServiceError>> validationException(HttpServletRequest httpServletRequest,
			final RegistrationException reqException) throws IOException {
		// ExceptionUtils.logRootCause(reqException);
		MainResponseDTO<ServiceError> errorResponse = setErrors(httpServletRequest);
		errorResponse.getErrors().addAll(reqException.getList());

		return new ResponseEntity<>(errorResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(DataException.class)
	public ResponseEntity<MainResponseDTO<ServiceError>> getDataException(final DataException dataException)
			throws IOException {
		MainResponseDTO<ServiceError> errorResponse = new MainResponseDTO<ServiceError>();
		errorResponse.setStatus(false);
		errorResponse.setResponsetime(LocalDateTime.now());
		ServiceError serviceErrors = new ServiceError(dataException.getErrorCode(), dataException.getErrorMessage());
		errorResponse.getErrors().add(serviceErrors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ResponseEntity<MainResponseDTO<ServiceError>> defaultErrorHandler(
			final HttpServletRequest httpServletRequest, Exception e) throws IOException {
		MainResponseDTO<ServiceError> errorResponse = setErrors(httpServletRequest);
		errorResponse.getErrors().add(new ServiceError(AppointmentErrorCodes.INTERNAL_SERVER_ERROR.getErrorCode(),
				AppointmentErrorCodes.INTERNAL_SERVER_ERROR.getErrorMessage()));
		e.printStackTrace();
		// ExceptionUtils.logRootCause(e);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public MainResponseDTO<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		MainResponseDTO<Object> responseDto = new MainResponseDTO<Object>();
		List<ServiceError> errorList = new ArrayList<ServiceError>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			//String fieldName = ((FieldError) error).getField();
			String fieldName = AppointmentErrorCodes.BAD_REQUEST.getErrorCode();
			String errorMessage = error.getDefaultMessage();
			errorList.add(new ServiceError(fieldName, errorMessage));
		});
		responseDto.setStatus(false);
		responseDto.setErrors(errorList);
		responseDto.setResponsetime(LocalDateTime.now());
		return responseDto;
	}

	private MainResponseDTO<ServiceError> setErrors(HttpServletRequest httpServletRequest) throws IOException {
		MainResponseDTO<ServiceError> responseWrapper = new MainResponseDTO<>();
		responseWrapper.setResponsetime(LocalDateTime.now());
		String requestBody = null;
		if (httpServletRequest instanceof ContentCachingRequestWrapper) {
			requestBody = new String(((ContentCachingRequestWrapper) httpServletRequest).getContentAsByteArray());
		}
		if (EmptyCheckUtils.isNullEmpty(requestBody)) {
			return responseWrapper;
		}
		objectMapper.registerModule(new JavaTimeModule());
		return responseWrapper;
	}

	private List<ServiceError> getListOfFieldErrors(Errors errors) {
		List<ServiceError> serviceErrors = errors.getFieldErrors().stream()
				.map(error -> new ServiceError(AppointmentConstants.ERROR, error.getDefaultMessage()))
				.collect(Collectors.toList());
		return serviceErrors;
	}

}
