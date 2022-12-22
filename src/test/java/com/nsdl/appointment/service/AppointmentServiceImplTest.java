package com.nsdl.appointment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import com.nsdl.appointment.constants.AppointmentConstants;
import com.nsdl.appointment.dto.AppointmentDetails;
import com.nsdl.appointment.dto.DoctorDTO;
import com.nsdl.appointment.dto.DoctorSearchRequest;
import com.nsdl.appointment.dto.DoctorSlotRequest;
import com.nsdl.appointment.dto.PatientDetailsDTO;
import com.nsdl.appointment.dto.PatientReview;
import com.nsdl.appointment.dto.SaveAppDetailsRequest;
import com.nsdl.appointment.dto.SaveAppDetailsResponse;
import com.nsdl.appointment.dto.SlotDetails;
import com.nsdl.appointment.dto.UserDTO;
import com.nsdl.appointment.entity.AppointmentDtlsEntity;
import com.nsdl.appointment.entity.DocMstrDtlsEntity;
import com.nsdl.appointment.entity.DocSlotDtlsEntity;
import com.nsdl.appointment.entity.PatientRegDtlsEntity;
import com.nsdl.appointment.entity.PatientRevDtlsEntity;
import com.nsdl.appointment.entity.PaymentDtlsEntity;
import com.nsdl.appointment.exception.RegistrationException;
import com.nsdl.appointment.repository.AppointmentDtlsRepository;
import com.nsdl.appointment.repository.DocMstrDtlsRepository;
import com.nsdl.appointment.repository.DocSlotDtlsRepository;
import com.nsdl.appointment.repository.PatientRegDtlsRepository;
import com.nsdl.appointment.repository.PaymentDtlsRepository;
import com.nsdl.appointment.service.impl.AppointmentServiceImpl;

@RunWith(SpringRunner.class)
public class AppointmentServiceImplTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@InjectMocks
	private AppointmentServiceImpl appointmentServiceImpl;

	@Mock
	private DocMstrDtlsRepository docMstrDtlsRepository;

	@Mock
	private DocSlotDtlsRepository docSlotDtlsRepository;

	@Mock
	private PatientRegDtlsRepository patientRegDtlsRepository;

	@Mock
	private PaymentDtlsRepository paymentDtlsRepository;

	@Mock
	private AppointmentDtlsRepository appointmentDtlsRepository;

	@Mock
	private UserDTO userDetails;

	@Mock
	private AuditService auditService;

	SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");

	List<DocMstrDtlsEntity> docMstrDtlsEntities = new ArrayList<>();
	List<DocSlotDtlsEntity> docSlotDtlsEntities = new ArrayList<>();
	List<PatientRevDtlsEntity> patientRevDtlsEntities = new ArrayList<>();
	List<SlotDetails> slotDetails = new ArrayList<>();
	SlotDetails slot = new SlotDetails();

	@Before
	public void setUp() throws ParseException {

		DocMstrDtlsEntity docMstrDtlsEntity = new DocMstrDtlsEntity();
		docMstrDtlsEntity.setDmdDrName("amol");
		docMstrDtlsEntity.setDmdSpecialiazation("dental");
		docMstrDtlsEntity.setDmdConsulFee(100);
		docMstrDtlsEntity.setDmdUserId("722");
		docMstrDtlsEntities.add(docMstrDtlsEntity);

		DocSlotDtlsEntity docSlotDtlsEntity = new DocSlotDtlsEntity();
		docSlotDtlsEntity.setDsdSlot("11.30");
		docSlotDtlsEntity.setDsdSlotDate(sdfo.parse("2020-11-17"));
		docSlotDtlsEntities.add(docSlotDtlsEntity);

		PatientRevDtlsEntity patientRevDtlsEntity = new PatientRevDtlsEntity();
		patientRevDtlsEntity.setPrdRating((long) 0);
		patientRevDtlsEntities.add(patientRevDtlsEntity);
		docMstrDtlsEntity.setPatientRevDtlsEntities(patientRevDtlsEntities);

		slot.setSlotDate("2020-11-17");
		slot.setSlotTime("11.30");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getListOfDoctorsTest() throws Exception {
		DoctorSearchRequest doctorSearchRequest = new DoctorSearchRequest();
		doctorSearchRequest.setDoctorName("Amol");
		doctorSearchRequest.setGender("male");
		doctorSearchRequest.setSpeciality("dental");
		doctorSearchRequest.setConsultFee(new int[] { 0, 100 });
		doctorSearchRequest.setAvailabilityStartDate(new Date());
		doctorSearchRequest.setAvailabilityEndDate(new Date());

		List<DoctorDTO> doctorDetails = new ArrayList<>();
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDoctorId("722");
		doctorDTO.setDoctorName("amol");
		doctorDTO.setSpeciality("dental");
		doctorDTO.setConsultFee(100);

		slotDetails.add(slot);
		doctorDTO.setSlotDetails(slotDetails);

		PatientReview patientReview = new PatientReview();
		patientReview.setReviewRating(0.0);
		patientReview.setPatientStoryCnt((long) 1);

		doctorDTO.setPatientReview(patientReview);
		doctorDTO.setAvailability("2020-11-17");
		doctorDetails.add(doctorDTO);

		Mockito.when(docMstrDtlsRepository.findAll(Mockito.<Specification>any())).thenReturn(docMstrDtlsEntities);
		/*Mockito.when(docSlotDtlsRepository.findAllDocSlotDtls(Mockito.anyString(), Mockito.any(), Mockito.any()))
				.thenReturn(docSlotDtlsEntities);*/

		assertThat(doctorDetails).containsExactlyInAnyOrderElementsOf(
				appointmentServiceImpl.getListOfDoctorBySearch(doctorSearchRequest));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(expected = RegistrationException.class)
	public void getListOfDoctorsNegativeTest() throws Exception {
		DoctorSearchRequest doctorSearchRequest = new DoctorSearchRequest();
		doctorSearchRequest.setDoctorName("Amol");
		doctorSearchRequest.setGender("male");
		doctorSearchRequest.setSpeciality("dental");
		doctorSearchRequest.setConsultFee(new int[] { 0, 100 });
		doctorSearchRequest.setAvailabilityStartDate(new Date());
		doctorSearchRequest.setAvailabilityEndDate(new Date());

		List<DocMstrDtlsEntity> docMstrDtlsEntities = new ArrayList<>();

		Mockito.when(docMstrDtlsRepository.findAll(Mockito.<Specification>any())).thenReturn(docMstrDtlsEntities);

		appointmentServiceImpl.getListOfDoctorBySearch(doctorSearchRequest);
	}

	@Test
	public void getAvailableSlotListByDoctorTest() throws Exception {
		DoctorSlotRequest doctorSlotRequest = new DoctorSlotRequest();
		doctorSlotRequest.setDoctorUserId("722");
		doctorSlotRequest.setSlotDate(sdfo.parse("2020-07-17"));

		slotDetails.add(slot);

		Mockito.when(docSlotDtlsRepository.findDocSlotDtlsByDate(Mockito.anyString(), Mockito.any()))
				.thenReturn(docSlotDtlsEntities);

		assertThat(slotDetails).containsExactlyInAnyOrderElementsOf(
				appointmentServiceImpl.getAvailableSlotListByDoctor(doctorSlotRequest));
	}

	@Test(expected = RegistrationException.class)
	public void getAvailableSlotListByDoctorNegativeTest() throws Exception {
		DoctorSlotRequest doctorSlotRequest = new DoctorSlotRequest();
		doctorSlotRequest.setDoctorUserId("722");
		doctorSlotRequest.setSlotDate(sdfo.parse("2020-07-17"));

		List<DocSlotDtlsEntity> docSlotDtlsEntities = new ArrayList<>();

		Mockito.when(docSlotDtlsRepository.findDocSlotDtlsByDate(Mockito.anyString(), Mockito.any()))
				.thenReturn(docSlotDtlsEntities);

		appointmentServiceImpl.getAvailableSlotListByDoctor(doctorSlotRequest);
	}

	@Test
	public void getPatientDetailsTest() throws Exception {

		String patientId = "pt2";
		PatientRegDtlsEntity patientDetails = new PatientRegDtlsEntity();
		patientDetails.setPrdEmail("sunil@gmail.com");
		patientDetails.setPrdMobileNo("8753581345");
		patientDetails.setPrdPtName("sunil");

		PatientDetailsDTO patientDetailsDTO = new PatientDetailsDTO();
		patientDetailsDTO.setPatientFullName("sunil");
		patientDetailsDTO.setMobileNumber("8753581345");
		patientDetailsDTO.setEmailId("sunil@gmail.com");

		Mockito.when(patientRegDtlsRepository.findByPrdUserId(Mockito.anyString())).thenReturn(patientDetails);

		/*assertThat(patientDetailsDTO)
				.isEqualToComparingFieldByField(appointmentServiceImpl.getPatientDetails(patientId));*/
	}

	@Test(expected = RegistrationException.class)
	public void getPatientDetailsNegativeTest() throws Exception {

		String patientId = "pt2";
		PatientRegDtlsEntity patientDetails = null;

		/*Mockito.when(patientRegDtlsRepository.findByPrdUserId(Mockito.anyString())).thenReturn(patientDetails);
		appointmentServiceImpl.getPatientDetails(patientId);*/
	}

	@Test
	public void saveAppointmentDetailsTest() throws Exception {

		SaveAppDetailsResponse response = new SaveAppDetailsResponse();
		response.setInfo(AppointmentConstants.APPOINTMENT_SAVED_SUCCESSFULLY);
		response.setAppointmentID("c2f4e41d-c760-41b2-80f1-d2cf4bf62aeb");

		SaveAppDetailsRequest appointmentRequest = new SaveAppDetailsRequest();
		appointmentRequest.setDrRegID("722");
		appointmentRequest.setPtRegID("pt2");
		appointmentRequest.setTransactionID("123423423");

		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setAppointmentSlot("20.30");
		appointmentDetails.setAppointmentDate(new Date());
		appointmentRequest.setAppointmentDetails(appointmentDetails);

		PaymentDtlsEntity paymentDtlsEntity = new PaymentDtlsEntity();
		PatientRegDtlsEntity patientDetails = new PatientRegDtlsEntity();
		DocMstrDtlsEntity docMstrDtlsEntity = new DocMstrDtlsEntity();
		AppointmentDtlsEntity appointmentDtlsEntity = new AppointmentDtlsEntity();
		appointmentDtlsEntity.setAdApptId("c2f4e41d-c760-41b2-80f1-d2cf4bf62aeb");

		Mockito.when(paymentDtlsRepository.findByPdPmtTransId(Mockito.anyString())).thenReturn(paymentDtlsEntity);
		Mockito.when(docMstrDtlsRepository.findByDmdUserId(Mockito.anyString())).thenReturn(docMstrDtlsEntity);
		Mockito.when(patientRegDtlsRepository.findByPrdUserId(Mockito.anyString())).thenReturn(patientDetails);
		Mockito.when(userDetails.getUserName()).thenReturn("nitin");
		Mockito.when(appointmentDtlsRepository.save(Mockito.any())).thenReturn(appointmentDtlsEntity);
		Mockito.when(auditService.auditAppointmentData(appointmentDtlsEntity)).thenReturn(true);

		assertThat(response)
				.isEqualToComparingFieldByField(appointmentServiceImpl.saveAppointmentDetails(appointmentRequest));
	}

	@Test(expected = RegistrationException.class)
	public void saveAppointmentDetailsNegativeTest() throws Exception {

		SaveAppDetailsRequest appointmentRequest = new SaveAppDetailsRequest();
		appointmentRequest.setDrRegID("722");
		appointmentRequest.setPtRegID("pt2");
		appointmentRequest.setTransactionID("123423423");

		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setAppointmentSlot("20.30");
		appointmentDetails.setAppointmentDate(sdfo.parse("2020-10-17"));
		appointmentRequest.setAppointmentDetails(appointmentDetails);
		
		appointmentServiceImpl.saveAppointmentDetails(appointmentRequest);
	}

}
