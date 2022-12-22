package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "appt_dtls_aud", schema = "audit")
public class AppointmentDtlsAuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "ad_id_pk")
	private Long adIdPk;

	@Column(name = "ad_appt_id")
	private String adApptId;
	
	@Column(name = "ad_dr_user_id_fk", nullable = false)
	private String adDrUserIdFk;
	
	@Column(name = "ad_pt_user_id_fk", nullable = false)
	private String adPtUserIdFk;

	@Column(name = "ad_scrb_user_id")
	private String adScrbUserId;
	
	@Column(name = "ad_appt_slot_fk", nullable = false)
	private String adApptSlotFk;

	@Column(name = "ad_appt_date_fk", nullable = false)
	private Date adApptDateFk;

	@Column(name = "ad_appt_booked_for")
	private String adApptBookedFor;

	@Column(name = "ad_isbooked")
	private boolean adIsbooked;

	@Column(name = "ad_appt_status")
	private String adApptStatus;
	
	@Column(name = "ad_pmt_trans_id_fk", nullable = false)
	private String adPmtTransIdFk;

	@Column(name = "ap_cancel_reason")
	private String apCancelReason;

	@Column(name = "ap_cancel_date")
	private LocalDate apCancelDate;

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
	
	@Column(name = "user_id")
	private String userId;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@Column(name="ad_patient_dob")
    private Date adPatientDob;
	
	@Column(name = "ad_assist_user_id")
	private String adAssistUserId;

}
