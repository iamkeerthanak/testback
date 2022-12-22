package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pt_reg_dtls", schema = "registration")
public class PatientRegDtlsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "prd_id_pk")
	private Long prdIdPk;

	@Column(name = "prd_user_id", nullable = false, unique = true)
	private String prdUserId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "prd_dr_user_id_fk", referencedColumnName = "prd_user_id")
	private List<PatientRevDtlsEntity> patientRevDtlsEntities;

	@OneToMany(mappedBy = "patientRegDtlsEntity")
	private List<AppointmentDtlsEntity> appointmentDtlsEntity;

	@Column(name = "prd_pt_name", nullable = false)
	private String prdPtName;

	@Column(name = "prd_password", nullable = false)
	private String prdPassword;

	@Column(name = "prd_mobile_no", nullable = false)
	private String prdMobileNo;

	@Column(name = "prd_email")
	private String prdEmail;
	
	@Column(name = "prd_photo_path")
	private String prdPhotoPath;

	@Column(name = "prd_gender")
	private String prdGender;

	@Column(name = "prd_dob")
	private Date prdDob;

	@Column(name = "prd_blood_grp")
	private String prdBloodGrp;
	
}
