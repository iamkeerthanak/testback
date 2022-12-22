package com.nsdl.appointment.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "consultation_history", schema = "appointment")
public class ConsultationHistoryEntity {
	
	@Id
	@Column(name = "ch_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@Column(name = "ch_usr_id" , nullable=true)
	private String ch_usr_id;
	
	@Column(name = "ch_session_id")
	private String ch_session_id;
	
	@Column(name = "ch_appt_id", nullable=true)
	private String ch_appt_id;
	
	@Column(name = "ch_created_tmpstmp" , nullable=true)
	private LocalDateTime ch_created_tmpstmp;

	@Column(name = "ch_slot_time", nullable=true)
	private String ch_slot_time;
	
	@Column(name = "ch_patient_id", nullable=true)
	private String ch_patient_id;
	
	@Column(name = "ch_flag")
	private String ch_flag;
	
	public ConsultationHistoryEntity() {
		super();
		
	}

	public ConsultationHistoryEntity(Long id, String ch_usr_id, String ch_session_id, String ch_appt_id,
			LocalDateTime ch_created_tmpstmp, String ch_slot_time, String ch_patient_id, String ch_flag) {
		super();
		Id = id;
		this.ch_usr_id = ch_usr_id;
		this.ch_session_id = ch_session_id;
		this.ch_appt_id = ch_appt_id;
		this.ch_created_tmpstmp = ch_created_tmpstmp;
		this.ch_slot_time = ch_slot_time;
		this.ch_patient_id = ch_patient_id;
		this.ch_flag = ch_flag;
	}




	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public String getCh_usr_id() {
		return ch_usr_id;
	}



	public void setCh_usr_id(String ch_usr_id) {
		this.ch_usr_id = ch_usr_id;
	}



	public String getCh_session_id() {
		return ch_session_id;
	}



	public void setCh_session_id(String ch_session_id) {
		this.ch_session_id = ch_session_id;
	}



	public String getCh_appt_id() {
		return ch_appt_id;
	}



	public void setCh_appt_id(String ch_appt_id) {
		this.ch_appt_id = ch_appt_id;
	}



	public LocalDateTime getCh_created_tmpstmp() {
		return ch_created_tmpstmp;
	}



	public void setCh_created_tmpstmp(LocalDateTime ch_created_tmpstmp) {
		this.ch_created_tmpstmp = ch_created_tmpstmp;
	}

	public String getCh_slot_time() {
		return ch_slot_time;
	}

	public void setCh_slot_time(String ch_slot_time) {
		this.ch_slot_time = ch_slot_time;
	}

	public String getCh_patient_id() {
		return ch_patient_id;
	}




	public void setCh_patient_id(String ch_patient_id) {
		this.ch_patient_id = ch_patient_id;
	}




	public String isCh_flag() {
		return ch_flag;
	}




	public void setCh_flag(String ch_flag) {
		this.ch_flag = ch_flag;
	}

	
}


	
	