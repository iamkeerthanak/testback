package com.nsdl.appointment.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="assistant_reg_dtls", schema = "registration")
public class AssistantRegDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assistant_reg_generator")
	@SequenceGenerator(name = "assistant_reg_generator", sequenceName = "registration.assistant_reg_dtls_seq", allocationSize = 1)
	@Column(name="ard_id_pk")
	private Integer ardIdPk;

	@Column(name="ard_assist_name")
	private String assistantName;

	@Column(name="ard_user_id")
	private String assistantUserId;

	@Column(name="ard_password")
	private String password;

	@Column(name="ard_mobile_no")
	private Long mobileNo;
	
	@Column(name="ard_email")
	private String email;
	
	@Column(name="ard_dr_user_id_fk")
	private String drUserIdFk;

	@Column(name="ard_address1")
	private String address1;

	@Column(name="ard_address2")
	private String address2;

	@Column(name="ard_address3")
	private String address3;

	@Column(name="ard_created_by")
	private String ardCreatedBy;

	
	@Column(name="ard_created_tmstmp")
	private Timestamp ardCreatedTmstmp;
	
	
	@Column(name="ard_modified_by")
	private String ardModifiedBy;
	
	
	@Column(name="ard_modified_tmstmp")
	private Timestamp ardModifiedTmstmp;

	
	@Column(name="ard_photo_path")
	private String photoPath;

	@Column(name="ard_gender")
	private String gender;

	@Column(name="ard_state")
	private String state;
	
	@Column(name="ard_city")
	private String city;

	@Column(name="ard_assist_first_name")
	private String ardAssistFirstName;

	@Column(name="ard_assist_middle_name")
	private String ardAssistMiddleName;

	@Column(name="ard_assist_last_name")
	private String ardAssistLastName;
	
}
