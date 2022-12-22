package com.nsdl.appointment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsdl.appointment.dto.AppointmentDetails;
import com.nsdl.appointment.dto.DoctorDTO;
import com.nsdl.appointment.dto.DoctorSearchRequest;
import com.nsdl.appointment.dto.DoctorSlotRequest;
import com.nsdl.appointment.dto.MainRequestDTO;
import com.nsdl.appointment.dto.MainResponseDTO;
import com.nsdl.appointment.dto.PatientDetailsDTO;
import com.nsdl.appointment.dto.PatientDetailsRequest;
import com.nsdl.appointment.dto.PatientReview;
import com.nsdl.appointment.dto.SaveAppDetailsRequest;
import com.nsdl.appointment.dto.SaveAppDetailsResponse;
import com.nsdl.appointment.dto.SlotDetails;
import com.nsdl.appointment.exception.ServiceError;
import com.nsdl.appointment.service.AppointmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentControllerTest {

	public MockMvc mockMvc;

	@Autowired
	public WebApplicationContext context;

	@MockBean
	private AppointmentService appointmentService;

	@Autowired
	public ObjectMapper objectMapper;

	List<DoctorDTO> doctorDetails = new ArrayList<>();
	DoctorDTO doctorDTO = new DoctorDTO();
	SlotDetails details = new SlotDetails();
	List<SlotDetails> slotDetails = new ArrayList<>();
	PatientReview patientReview = new PatientReview();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		doctorDTO.setDoctorId("722");
		doctorDTO.setDoctorName("Amol");
		doctorDTO.setSpeciality("dental");
		doctorDTO.setConsultFee(100);

		details.setSlotDate("2020-10-23");
		details.setSlotTime("11.30");
		slotDetails.add(details);

		doctorDTO.setSlotDetails(slotDetails);
		patientReview.setReviewRating(2.5);
		patientReview.setPatientStoryCnt((long) 5);

		doctorDTO.setPatientReview(patientReview);
		doctorDetails.add(doctorDTO);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getListOfDoctorsTest() throws Exception {

		MainRequestDTO<DoctorSearchRequest> request = new MainRequestDTO<>();
		DoctorSearchRequest doctorSearchRequest = new DoctorSearchRequest();
		doctorSearchRequest.setDoctorName("Amol");
		doctorSearchRequest.setGender("male");
		doctorSearchRequest.setSpeciality("dental");
		doctorSearchRequest.setConsultFee(new int[] { 0, 100 });
		doctorSearchRequest.setAvailabilityStartDate(new Date());
		doctorSearchRequest.setAvailabilityEndDate(new Date());
		request.setRequest(doctorSearchRequest);

		Mockito.when(appointmentService.getListOfDoctorBySearch(doctorSearchRequest)).thenReturn(doctorDetails);
		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc
				.perform(post("/getListOfDoctorBySearch").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<List<DoctorDTO>> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		List<DoctorDTO> actualResponse = objectMapper.convertValue(responseWrapperActual.getResponse(),
				new TypeReference<List<DoctorDTO>>() {
				});
		assertThat(actualResponse).containsExactlyInAnyOrderElementsOf(doctorDetails);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getListOfDoctorsNegativeTest() throws Exception {

		MainRequestDTO<DoctorSearchRequest> request = new MainRequestDTO<>();
		DoctorSearchRequest doctorSearchRequest = new DoctorSearchRequest();
		doctorSearchRequest.setDoctorName("Amol");
		doctorSearchRequest.setGender("male");
		doctorSearchRequest.setSpeciality(null);
		doctorSearchRequest.setConsultFee(new int[] { 0, 100 });
		doctorSearchRequest.setAvailabilityStartDate(new Date());
		doctorSearchRequest.setAvailabilityEndDate(new Date());
		request.setRequest(doctorSearchRequest);

		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc
				.perform(post("/getListOfDoctorBySearch").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<List<DoctorDTO>> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		List<ServiceError> errors = responseWrapperActual.getErrors();
		assertEquals(1, errors.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getSlotListByDoctorTest() throws Exception {

		MainRequestDTO<DoctorSlotRequest> request = new MainRequestDTO<>();
		DoctorSlotRequest doctorSlotRequest = new DoctorSlotRequest();
		doctorSlotRequest.setDoctorUserId("722");
		doctorSlotRequest.setSlotDate(new Date());
		request.setRequest(doctorSlotRequest);

		Mockito.when(appointmentService.getAvailableSlotListByDoctor(doctorSlotRequest)).thenReturn(slotDetails);
		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(
				post("/getAvailableSlotListByDoctor").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<List<SlotDetails>> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		List<SlotDetails> actualResponse = objectMapper.convertValue(responseWrapperActual.getResponse(),
				new TypeReference<List<SlotDetails>>() {
				});
		assertThat(actualResponse).containsExactlyInAnyOrderElementsOf(slotDetails);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getPatientDetailsTest() throws Exception {

		MainRequestDTO<PatientDetailsRequest> request = new MainRequestDTO<>();
		PatientDetailsRequest patientDetailsRequest = new PatientDetailsRequest();
		patientDetailsRequest.setPtRegID("pt3");
		request.setRequest(patientDetailsRequest);

		PatientDetailsDTO patientDetails = new PatientDetailsDTO();
		patientDetails.setEmailId("pt@gmail.com");
		patientDetails.setMobileNumber("9823845566");
		patientDetails.setPatientFullName("aman");

		/*Mockito.when(appointmentService.getPatientDetails(patientDetailsRequest))
				.thenReturn(patientDetails);*/
		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc
				.perform(post("/getPatientDetails").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<PatientDetailsDTO> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		PatientDetailsDTO actualResponse = objectMapper.convertValue(responseWrapperActual.getResponse(),
				PatientDetailsDTO.class);
		assertThat(actualResponse).isEqualToComparingFieldByField(patientDetails);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveAppointmentDetailsTest() throws Exception {

		MainRequestDTO<SaveAppDetailsRequest> request = new MainRequestDTO<>();
		SaveAppDetailsRequest saveAppDetailsRequest = new SaveAppDetailsRequest();
		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setAppointmentDate(new Date());
		appointmentDetails.setAppointmentSlot("11.30");
		saveAppDetailsRequest.setAppointmentDetails(appointmentDetails);
		saveAppDetailsRequest.setDrRegID("722");
		saveAppDetailsRequest.setPtRegID("pt2");
		saveAppDetailsRequest.setTransactionID("42342");
		request.setRequest(saveAppDetailsRequest);

		SaveAppDetailsResponse appointmentResponse = new SaveAppDetailsResponse();
		appointmentResponse.setAppointmentID("5487234678342384");
		appointmentResponse.setInfo("booked successfully");

		Mockito.when(appointmentService.saveAppointmentDetails(saveAppDetailsRequest))
				.thenReturn(appointmentResponse);
		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc
				.perform(post("/saveAppointmentDetails").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<SaveAppDetailsResponse> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		SaveAppDetailsResponse actualResponse = objectMapper.convertValue(responseWrapperActual.getResponse(),
				SaveAppDetailsResponse.class);
		assertThat(actualResponse).isEqualToComparingFieldByField(appointmentResponse);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveAppointmentDetailsNegativeTest() throws Exception {

		MainRequestDTO<SaveAppDetailsRequest> request = new MainRequestDTO<>();
		SaveAppDetailsRequest saveAppDetailsRequest = new SaveAppDetailsRequest();
		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setAppointmentDate(new Date());
		appointmentDetails.setAppointmentSlot("11.30");
		saveAppDetailsRequest.setAppointmentDetails(appointmentDetails);
		saveAppDetailsRequest.setDrRegID(null);
		saveAppDetailsRequest.setPtRegID(null);
		saveAppDetailsRequest.setTransactionID("42342");
		request.setRequest(saveAppDetailsRequest);

		String jsonRequest = objectMapper.writeValueAsString(request);
		MvcResult mvcResult = mockMvc
				.perform(post("/saveAppointmentDetails").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = mvcResult.getResponse().getContentAsString();
		MainResponseDTO<SaveAppDetailsResponse> responseWrapperActual = objectMapper.readValue(resultContent,
				MainResponseDTO.class);
		List<ServiceError> errors = responseWrapperActual.getErrors();
		assertEquals(2, errors.size());
	}

}
