package com.nsdl.appointment.service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.SpringRunner;

import com.nsdl.appointment.dto.UserDTO;
import com.nsdl.appointment.entity.AppointmentDtlsAuditEntity;
import com.nsdl.appointment.entity.AppointmentDtlsEntity;
import com.nsdl.appointment.entity.DocMstrDtlsEntity;
import com.nsdl.appointment.entity.PatientRegDtlsEntity;
import com.nsdl.appointment.entity.PaymentDtlsEntity;
import com.nsdl.appointment.repository.AppointmentDtlsAuditRepository;
import com.nsdl.appointment.service.impl.AuditServiceImpl;

@RunWith(SpringRunner.class)
public class AuditServiceImplTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@InjectMocks
	private AuditServiceImpl auditServiceImpl;
	
	@Mock
	private AppointmentDtlsAuditRepository auditRepository;

	@Mock
	private UserDTO userDetails;

	@Before
	public void setUp() throws ParseException {
	}
	
	@Test
	public void auditAppointmentDataTest() throws Exception {

		AppointmentDtlsEntity appointmentDtlsEntity = new AppointmentDtlsEntity();
		
		AppointmentDtlsAuditEntity appointmentDtlsAuditEntity = new AppointmentDtlsAuditEntity();
		
		DocMstrDtlsEntity docMstrDtlsEntity = new DocMstrDtlsEntity();
		docMstrDtlsEntity.setDmdDrName("amol");
		docMstrDtlsEntity.setDmdSpecialiazation("dental");
		docMstrDtlsEntity.setDmdConsulFee(100);
		docMstrDtlsEntity.setDmdUserId("722");
		appointmentDtlsEntity.setDocMstrDtlsEntity(docMstrDtlsEntity);

		PaymentDtlsEntity paymentDtlsEntity = new PaymentDtlsEntity();
		appointmentDtlsEntity.setPaymentDtlsEntity(paymentDtlsEntity);
		
		PatientRegDtlsEntity patientRegDtlsEntity = new PatientRegDtlsEntity();
		appointmentDtlsEntity.setPatientRegDtlsEntity(patientRegDtlsEntity);
		
		appointmentDtlsEntity.setAdIdPk((long) 1);
		appointmentDtlsEntity.setAdApptId("2367218317");
		appointmentDtlsEntity.getDocMstrDtlsEntity().setDmdUserId("722");
		appointmentDtlsEntity.getPatientRegDtlsEntity().setPrdUserId("pt2");
		appointmentDtlsEntity.setAdApptDateFk(new Date());
		appointmentDtlsEntity.setAdApptSlotFk("11.30");
		appointmentDtlsEntity.setAdApptBookedFor("Y");
		appointmentDtlsEntity.setAdIsbooked(true);
		appointmentDtlsEntity.setAdApptStatus("S");
		appointmentDtlsEntity.getPaymentDtlsEntity().setPdPmtTransId("1223");
		appointmentDtlsEntity.setApCancelReason("");
		appointmentDtlsEntity.setApCancelDate(null);
		appointmentDtlsEntity.setAdCreatedBy("nitin");
		appointmentDtlsEntity.setAdCreatedTmstmp(LocalDateTime.now());
		appointmentDtlsEntity.setAdModifiedBy("nitin");
		appointmentDtlsEntity.setAdModifiedTmstmp(LocalDateTime.now());
		appointmentDtlsEntity.setAdOptiVersion(0);

		Mockito.when(userDetails.getUserName()).thenReturn("nitin");
		Mockito.when(auditRepository.save(Mockito.any())).thenReturn(appointmentDtlsAuditEntity);

		assertTrue(auditServiceImpl.auditAppointmentData(appointmentDtlsEntity));
	}

}
