package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;


/**
 * The persistent class for the appt_dr_scrb_assign_dtls database table.
 * 
 */
@Entity
@Table(name="appt_dr_scrb_assign_dtls",schema="appointment")
@Data
public class ApptDrScrbAssignDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="adsad_id_pk")
	private Integer idPk;

	@Column(name="adsad_created_by")
	private String createdBy;

	@Column(name="adsad_created_tmstmp")
	private LocalDateTime createdTmstmp;

	@Column(name="adsad_date")
	private Date date;

	@Column(name="adsad_dr_user_id_fk")
	private String drUserIdFk;

	@Column(name="adsad_scribe_user_id_fk")
	private String scribeUserIdFk;
	
	@Column(name="adsad_isactive")
	private Boolean isactive;

	@Column(name="adsad_modified_by")
	private String modifiedBy;

	@Column(name="adsad_modified_tmstmp")
	private LocalDateTime modifiedTmstmp;

	@Version
	@Column(name="adsad_opti_version")
	private Integer optiVersion;
	
}