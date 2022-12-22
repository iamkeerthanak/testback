package com.nsdl.appointment.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsdl.appointment.dto.MainRequestDTO;
import com.nsdl.appointment.dto.PaymentDTO;
import com.nsdl.appointment.dto.UserDTO;
import com.nsdl.appointment.entity.PaymentDtlsEntity;
import com.nsdl.appointment.repository.PaymentDtlsRepository;

@RestController
//@CrossOrigin("*")
@RequestMapping("/")
public class PaymentController {
	
	@Autowired
	private PaymentDtlsRepository paymentDtlsRepository;
	
	@Autowired
	private UserDTO userDetails;
	
	@PostMapping(value = "/dummyPaymentApi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> savePayment(@Valid @RequestBody MainRequestDTO<PaymentDTO> request) {
		
		PaymentDtlsEntity paymentDtlsEntity = new PaymentDtlsEntity();
		String role=userDetails.getRole();
		
		UUID uuid = UUID.randomUUID();
		paymentDtlsEntity.setPdPmtTransId(uuid.toString());
		paymentDtlsEntity.setPdPmtMode("online");
		paymentDtlsEntity.setPdAmount(100);
		paymentDtlsEntity.setPdBankRefTransId(new Random().nextInt(100000000));
		paymentDtlsEntity.setPdPmtDate(new Date());
		paymentDtlsEntity.setPdPmtStatus("Y");
		paymentDtlsEntity.setPdCardType("visa");
		paymentDtlsEntity.setPdCardNo(123463);
		paymentDtlsEntity.setPdNameOnCard("adam");
		paymentDtlsEntity.setPdCardValidity("07-2024");
		paymentDtlsEntity.setPrdOptiVersion(1);
		paymentDtlsEntity.setPdslotType(request.getRequest().getAppointmentType());
		//added for pay later option
		if(role.equalsIgnoreCase("doctor") || role.equalsIgnoreCase("receptionist") || role.equalsIgnoreCase("callcentre") || role.equalsIgnoreCase("assistant"))
		{
			paymentDtlsEntity.setApptBookedFor(request.getRequest().getBookedFor().toUpperCase());
			paymentDtlsEntity.setApptDate(request.getRequest().getAppointmentDate());
			paymentDtlsEntity.setApptSlot(request.getRequest().getAppointmentSlot());
			paymentDtlsEntity.setDocRegId(request.getRequest().getDocUserID());
			paymentDtlsEntity.setPtRegId(request.getRequest().getPtUserID());
			paymentDtlsEntity.setCreatedTimestmp(LocalDateTime.now());
		}else
		{
			paymentDtlsEntity.setApptBookedFor(null);
			paymentDtlsEntity.setApptDate(null);
			paymentDtlsEntity.setApptSlot(null);
			paymentDtlsEntity.setDocRegId(null);
			paymentDtlsEntity.setPtRegId(null);
			paymentDtlsEntity.setCreatedTimestmp(LocalDateTime.now());
		}
		
		paymentDtlsRepository.save(paymentDtlsEntity);
		return ResponseEntity.status(HttpStatus.OK).body(uuid.toString());
	}
	/*
	 * Added by SayaliA:To generate reference ID for payment , on link shared by doctor to patient externally, apart from system flow
	 * No token required because doctor is not login to system.
	 */
	@PostMapping(value = "/dummyPaymentApIForExternalLink", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> savePaymentDetails(@Valid @RequestBody MainRequestDTO<PaymentDTO> request) {
		
		PaymentDtlsEntity paymentDtlsEntity = new PaymentDtlsEntity();
		UUID uuid = UUID.randomUUID();
		paymentDtlsEntity.setPdPmtTransId(uuid.toString());
		paymentDtlsEntity.setPdPmtMode("online");
		paymentDtlsEntity.setPdAmount(100);
		paymentDtlsEntity.setPdBankRefTransId(new Random().nextInt(100000000));
		paymentDtlsEntity.setPdPmtDate(new Date());
		paymentDtlsEntity.setPdPmtStatus("Y");
		paymentDtlsEntity.setPdCardType("visa");
		paymentDtlsEntity.setPdCardNo(123463);
		paymentDtlsEntity.setPdNameOnCard("adam");
		paymentDtlsEntity.setPdCardValidity("07-2024");
		paymentDtlsEntity.setPrdOptiVersion(1);
		//added for pay later option
		paymentDtlsEntity.setApptBookedFor(request.getRequest().getBookedFor().toUpperCase());
		paymentDtlsEntity.setApptDate(request.getRequest().getAppointmentDate());
		paymentDtlsEntity.setApptSlot(request.getRequest().getAppointmentSlot());
		paymentDtlsEntity.setDocRegId(request.getRequest().getDocUserID());
		paymentDtlsEntity.setPtRegId(request.getRequest().getPtUserID());
		paymentDtlsEntity.setCreatedTimestmp(LocalDateTime.now());
		paymentDtlsRepository.save(paymentDtlsEntity);
		return ResponseEntity.status(HttpStatus.OK).body(uuid.toString());
	}


}
