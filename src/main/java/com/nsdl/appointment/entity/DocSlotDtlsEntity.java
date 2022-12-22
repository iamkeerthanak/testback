package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "dr_slot_dtls", schema = "appointment", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "dsd_dr_user_id_fk", " dsd_slot", " dsd_Slot_date", " dsd_isactive" }) })
public class DocSlotDtlsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "dsd_id_pk")
	private Long dsdIdPk;
	
	@Column(name = "dsd_dr_user_id_fk", nullable = false)
	private String dsdDrUserIdFk;
	
	@Column(name = "dsd_slot", nullable = false)
	private String dsdSlot;

	@Column(name = "dsd_Slot_date", nullable = false)
	private Date dsdSlotDate;

	@Column(name = "dsd_consul_fee", nullable = false)
	private Integer dsdConsulFee;

	@Column(name = "dsd_isactive", nullable = false)
	private Boolean dsdIsactive;

	@Column(name = "dsd_created_by")
	private String dsdCreatedBy;

	@Column(name = "dsd_created_tmstmp", nullable = false)
	private LocalDateTime dsdCreatedTmstmp;

	@Column(name = "dsd_modified_by")
	private String dsdModifiedBy;

	@Column(name = "dsd_modified_tmstmp", nullable = false)
	private LocalDateTime dsdModifiedTmstmp;

	@Version
	@Column(name = "dsd_opti_version")
	private Integer dsdOptiVersion;
	
	@Column(name = "dsd_slot_type")
	private String dsdSlotType;
}
