package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "appt_dtls", schema = "appointment", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"ad_dr_user_id_fk", " ad_appt_slot_fk", " ad_appt_date_fk", "ad_isbooked" }) })
public class AppointmentDtlsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ad_id_pk")
	private Long adIdPk;

	@Column(name = "ad_appt_id")
	private String adApptId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ad_dr_user_id_fk", referencedColumnName = "dmd_user_id", nullable = false)
	private DocMstrDtlsEntity docMstrDtlsEntity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ad_pt_user_id_fk", referencedColumnName = "prd_user_id", nullable = false)
	private PatientRegDtlsEntity patientRegDtlsEntity;

	@Column(name = "ad_appt_slot_fk", nullable = false)
	private String adApptSlotFk;

	@Column(name = "ad_appt_date_fk", nullable = false)
	private Date adApptDateFk;
	
	@Column(name = "ad_scrb_user_id")
	private String adScrbUserId;
	

	@Column(name = "ad_appt_booked_for", columnDefinition = "varchar(100) default 'Y'")
	private String adApptBookedFor;

	@Column(name = "ad_isbooked")
	private Boolean adIsbooked;

	@Column(name = "ad_appt_status", columnDefinition = "varchar(1) default 'S'")
	private String adApptStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ad_pmt_trans_id_fk", referencedColumnName = "pd_pmt_trans_id", nullable = false, unique = true)
	private PaymentDtlsEntity paymentDtlsEntity;

	@Column(name = "ap_cancel_reason")
	private String apCancelReason;

	@Column(name = "ap_cancel_date")
	private LocalDate apCancelDate;
	
	@Column(name = "ad_appt_start_time")
	private String adApptStartTime;
	
	@Column(name = "ad_created_by")
	private String adCreatedBy;

	@Column(name = "ad_created_tmstmp")
	private LocalDateTime adCreatedTmstmp;

	@Column(name = "ad_modified_by")
	private String adModifiedBy;

	@Column(name = "ad_modified_tmstmp")
	private LocalDateTime adModifiedTmstmp;

	@Version
	@Column(name = "ad_opti_version")
	private Integer adOptiVersion;
	
	@Column(name="ad_patient_email")
	private String adPatientEmail; 
	 
	@Column(name="ad_patient_mobile_no")
    private String adPatientMobileNo;
	
	@Column(name="ad_patient_dob")
    private Date adPatientDob;
	
	//added for receptionist and call centre role booking by sayali
	@Column(name = "ad_recpt_user_id")
	private String adReceptUserId;
	
	//added for consultation type by sayali
	@Column(name = "ad_appt_consult_type")
	private String adConsultType;
	
	//added for consultation type by sayali
	@Column(name = "ad_appt_symptoms")
	private String adSymptoms;
	
	@Column(name="ad_tc_flag")
	private Boolean adTcFlag;
	
	@Column(name = "ad_assist_user_id")
	private String adAssistUserId;
		
	@PrePersist
	private void prePersist() {
		adCreatedTmstmp = LocalDateTime.now();
		adModifiedTmstmp = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		adModifiedTmstmp = LocalDateTime.now();
	}
	
}
