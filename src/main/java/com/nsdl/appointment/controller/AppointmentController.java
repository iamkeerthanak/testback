package com.nsdl.appointment.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsdl.appointment.aspect.LoggingClientInfo;
import com.nsdl.appointment.dto.ApptDtlsDto;
import com.nsdl.appointment.dto.AssignScribeToApptRequest;
import com.nsdl.appointment.dto.AssignedScribeStatus;
import com.nsdl.appointment.dto.AssignedScribeToApptRequest;
import com.nsdl.appointment.dto.AssignedScribeToApptResponse;
import com.nsdl.appointment.dto.CancelAppointFilterRequest;
import com.nsdl.appointment.dto.CancelAppointListResponse;
import com.nsdl.appointment.dto.CancelAppointmentRequest;
import com.nsdl.appointment.dto.CancelAppointmentResponse;
import com.nsdl.appointment.dto.DoctorDTO;
import com.nsdl.appointment.dto.DoctorSearchRequest;
import com.nsdl.appointment.dto.DoctorSlotRequest;
import com.nsdl.appointment.dto.MainRequestDTO;
import com.nsdl.appointment.dto.MainResponseDTO;
import com.nsdl.appointment.dto.PatientDetailsDTO;
import com.nsdl.appointment.dto.PatientDetailsRequest;
import com.nsdl.appointment.dto.RescheduleApptDTO;
import com.nsdl.appointment.dto.SaveAppDetailsRequest;
import com.nsdl.appointment.dto.SaveAppDetailsResponse;
import com.nsdl.appointment.dto.ScribeAppptDetailsDTO;
import com.nsdl.appointment.dto.SlotDetails;
import com.nsdl.appointment.dto.StatusResponse;
import com.nsdl.appointment.dto.TokenDTO;
import com.nsdl.appointment.service.AppointmentService;
import com.nsdl.appointment.utils.AuthUtil;

@RestController
@RequestMapping("/")
//@CrossOrigin("*")
@LoggingClientInfo
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

	@PostMapping(value = "/getListOfDoctorBySearch", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<List<DoctorDTO>>> getListOfDoctors(
			@RequestBody @Valid MainRequestDTO<DoctorSearchRequest> doctorSearchRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<List<DoctorDTO>> response = (MainResponseDTO<List<DoctorDTO>>) AuthUtil
				.getMainResponseDto(doctorSearchRequest);

		response.setResponse(appointmentService.getListOfDoctorBySearch(doctorSearchRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/getAvailableSlotListByDoctor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<List<SlotDetails>>> getSlotListByDoctor(
			@RequestBody @Valid MainRequestDTO<DoctorSlotRequest> doctorSlotRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<List<SlotDetails>> response = (MainResponseDTO<List<SlotDetails>>) AuthUtil
				.getMainResponseDto(doctorSlotRequest);

		response.setResponse(appointmentService.getAvailableSlotListByDoctor(doctorSlotRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/getPatientDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<PatientDetailsDTO>> getPatientDetails(
			@RequestBody @Valid MainRequestDTO<PatientDetailsRequest> patientDetailsRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<PatientDetailsDTO> response = (MainResponseDTO<PatientDetailsDTO>) AuthUtil
				.getMainResponseDto(patientDetailsRequest);

		response.setResponse(appointmentService.getPatientDetails(patientDetailsRequest));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/saveAppointmentDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<SaveAppDetailsResponse>> saveAppointmentDetails(
			@RequestBody @Valid MainRequestDTO<SaveAppDetailsRequest> appointmentRequest) throws Exception {

		@SuppressWarnings("unchecked")
		MainResponseDTO<SaveAppDetailsResponse> response = (MainResponseDTO<SaveAppDetailsResponse>) AuthUtil
				.getMainResponseDto(appointmentRequest);

		response.setResponse(appointmentService.saveAppointmentDetails(appointmentRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/cancelAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<CancelAppointmentResponse>> cancelAppointment(
			@RequestBody @Valid MainRequestDTO<CancelAppointmentRequest> CancelAppointmentRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<CancelAppointmentResponse> response = (MainResponseDTO<CancelAppointmentResponse>) AuthUtil
				.getMainResponseDto(CancelAppointmentRequest);

		response.setResponse(appointmentService.cancelAppointment(CancelAppointmentRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/getAppointmentDetails")
	public ResponseEntity<MainResponseDTO<ApptDtlsDto>> getAppointmentDetails(
			@RequestParam(name = "apptId", required = true) String apptId) {
		MainResponseDTO<ApptDtlsDto> response = new MainResponseDTO<ApptDtlsDto>();
		response.setResponse(appointmentService.getAppointmentDetails(apptId));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * This API would be used for Rescheduling the scheduled appointments </br>
	 * 
	 * @author SayaliA
	 * @param MainRequestDTO<RescheduleApptDTO>
	 *            this is request body
	 * @return <MainResponseDTO<SaveAppDetailsResponse>> this is the response
	 */
	@PostMapping(value = "/saveRescheduleApptDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<SaveAppDetailsResponse>> saveRescheduleApptDetails(
			@RequestBody @Valid MainRequestDTO<RescheduleApptDTO> appointmentRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<SaveAppDetailsResponse> response = (MainResponseDTO<SaveAppDetailsResponse>) AuthUtil
				.getMainResponseDto(appointmentRequest);

		response.setResponse(appointmentService.saveReschdulesAppDetails(appointmentRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/assignScribeToApptByDoctor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<StatusResponse>> assignScribeToApptByDoctor(
			@RequestBody @Valid MainRequestDTO<AssignScribeToApptRequest> assignScribeToApptRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<StatusResponse> response = (MainResponseDTO<StatusResponse>) AuthUtil
				.getMainResponseDto(assignScribeToApptRequest);

		response.setResponse(appointmentService.assignScribeToApptByDoctor(assignScribeToApptRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/assignedApptListToScribe", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<AssignedScribeToApptResponse>> assignedApptListToScribe(
			@RequestBody @Valid MainRequestDTO<AssignedScribeToApptRequest> assignedScribeToApptRequest) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<AssignedScribeToApptResponse> response = (MainResponseDTO<AssignedScribeToApptResponse>) AuthUtil
				.getMainResponseDto(assignedScribeToApptRequest);

		response.setResponse(
				appointmentService.assignedApptListToScribe(assignedScribeToApptRequest.getRequest().getScribeID()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	//Chnge from get request to post as per requirment,SayaliA
	@PostMapping(value = "/isScribeAssignedToAppt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<AssignedScribeStatus>> isScribeAssignedToAppt(@RequestBody @Valid MainRequestDTO<ScribeAppptDetailsDTO> request) {
		MainResponseDTO<AssignedScribeStatus> response = new MainResponseDTO<AssignedScribeStatus>();
		response.setResponse(appointmentService.isScribeAssignedToAppt(request));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/getPatientCompleteAppointmentDetails")
	public ResponseEntity<MainResponseDTO<List<ApptDtlsDto>>> getPatientCompleteAppointmentDetails(
			@RequestParam(name = "ptUserId", required = true) String ptUserId) {
		MainResponseDTO<List<ApptDtlsDto>> response = new MainResponseDTO<List<ApptDtlsDto>>();
		response.setResponse(appointmentService.getPatientCompleteAppointmentDetails(ptUserId));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Method added by girishk to send notification to patient/doctor before appointment start.
	 */
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void sendNotificationBeforeAppointmentStart() {
		logger.info("Request received to send Notification Before Appointment Start");
		appointmentService.sendNotificationBeforeAppointmentStart();
	}
	 
	 
	/**
	 * Added by sayaliA to book appointment online by patient with link shared by doctor
	 * Half related code has been written in slot management service
	 */
	@PostMapping(value = "/bookAppointmentOnline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<List<DoctorDTO>>> bookAppointmentOnline(
			@RequestBody MainRequestDTO<TokenDTO> request) {

		@SuppressWarnings("unchecked")
		MainResponseDTO<List<DoctorDTO>> response = (MainResponseDTO<List<DoctorDTO>>) AuthUtil
				.getMainResponseDto(request);

		response.setResponse(appointmentService.getAppointmentListForDrID(request.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/*
	 * Added by SayaliA:Method added To Book Appointment by Patient on link shared by doctor
	 * No token present of doctor, because doctor is not getting login to system, external flow from system.
	 */
	@PostMapping(value = "/saveAppointmentDetailsForPatientByLink", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<SaveAppDetailsResponse>> saveAppointmentDetailsForPatient(
			@RequestBody @Valid MainRequestDTO<SaveAppDetailsRequest> appointmentRequest) throws Exception {
		@SuppressWarnings("unchecked")
		MainResponseDTO<SaveAppDetailsResponse> response = (MainResponseDTO<SaveAppDetailsResponse>) AuthUtil
				.getMainResponseDto(appointmentRequest);

		response.setResponse(appointmentService.saveAppointmentDetailsForPatient(appointmentRequest.getRequest()));
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping(path = "/getCancelAppointList",produces   = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<List<CancelAppointListResponse>>> getAllCancelAppointmentList() {
		logger.info("Call for get list of cancel appointments");
		MainResponseDTO<List<CancelAppointListResponse>> response = new MainResponseDTO<List<CancelAppointListResponse>>();
		List<CancelAppointListResponse> cancelAppointmentList = appointmentService.getCancelAppointmentList();
		response.setResponse(cancelAppointmentList);
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(path = "/getCancelAppointListByFilter", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<List<CancelAppointListResponse>>> getCancelAppointmentListByFilter(@RequestBody MainRequestDTO<CancelAppointFilterRequest> request){
		logger.info("Call for get list of cancel appointments");
		MainResponseDTO<List<CancelAppointListResponse>> response = new MainResponseDTO<List<CancelAppointListResponse>>();
		List<CancelAppointListResponse> cancelAppointmentList = appointmentService.getCancelAppointmentsByFilter(request.getRequest());
		response.setResponse(cancelAppointmentList);
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		logger.info("Returning Response");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping(path = "/updateStartTimeByAppointmentId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MainResponseDTO<String>> updateStartTimeByAppointmentId(
			@RequestParam(required = true) String appointmentId) {
		MainResponseDTO<String> response = new MainResponseDTO<String>();
		String status = appointmentService.updateStartTimeByAppointmentId(appointmentId);
		response.setResponse(status);
		response.setResponsetime(LocalDateTime.now());
		response.setStatus(true);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
