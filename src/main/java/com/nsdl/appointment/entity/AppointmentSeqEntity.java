package com.nsdl.appointment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "appt_seq", schema = "appointment")
public class AppointmentSeqEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "aas_id_pk")
	private Long aasIdPk;
	
	@Column(name = "aas_seq")
	private Long seq;

	@Version
	@Column(name = "aas_opti_version")
	private Integer optiVersion;

	
}
