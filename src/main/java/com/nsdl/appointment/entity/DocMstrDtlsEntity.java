package com.nsdl.appointment.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "dr_mstr_dtls", schema = "registration")
public class DocMstrDtlsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "dmd_id_pk")
	private Long dmdIdPk;

	@Column(name = "dmd_dr_name", nullable = false)
	private String dmdDrName;

	@Column(name = "dmd_user_id", nullable = false, unique = true)
	private String dmdUserId;

	@Column(name = "dmd_password", nullable = false)
	private String dmdPassword;

	@Column(name = "dmd_mobile_No", nullable = false, unique = true)
	private Long dmdMobileNo;

	@Column(name = "dmd_email")
	private String dmdEmail;
	
	@Column(name = "dmd_photo_path")
	private String dmdPhotoPath;

	@Column(name = "dmd_gender", nullable = false)
	private String dmdGender;

	@Column(name = "dmd_smc_number", nullable = false)
	private String dmdSmcNumber;

	@Column(name = "dmd_mci_number", nullable = false)
	private String dmdMciNumber;

	@Column(name = "dmd_Specialiazation", nullable = false)
	private String dmdSpecialiazation;

	@Column(name = "dmd_consul_fee")
	private Integer dmdConsulFee;

	@Column(name = "dmd_isverified", nullable = false)
	private Boolean dmdIsverified;

	@Column(name = "dmd_is_reg_by_ipan", columnDefinition = "varchar(1) default 'V'", nullable = false)
	private String dmdIsRegByIpan;

	@Column(name = "dmd_modified_By")
	private String dmdModifiedBy;
	
	@Column(name="dmd_dr_link")
	private String dmdDrLink;
	
	@Column(name="dmd_dr_profile_link")
	private String dmdDrProfileLink;

	@Column(name = "dmd_modified_tmstmp", nullable = false)
	private LocalDateTime dmdModifiedTmstmp;
	
	@Column(name = "dmd_pre_assessment_link", nullable = true)
	private String dmdPreAssessmentLink;

	@Column(name = "dmd_pre_assessment_flag", nullable = true)
	private boolean dmdPreAssessmentFlag;
	
	@Column(name = "dmd_short_pre_assessment_link", nullable = true)
	private String dmdShortPreAssessmentLink;

	@Version
	@Column(name = "dmd_opti_version")
	private Integer dmdOptiVersion;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dsd_dr_user_id_fk", referencedColumnName = "dmd_user_id")
	private List<DocSlotDtlsEntity> docSlotDtlsEntities;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "prd_dr_user_id_fk", referencedColumnName = "dmd_user_id")
	private List<PatientRevDtlsEntity> patientRevDtlsEntities;

	@OneToMany(mappedBy = "docMstrDtlsEntity")
	private List<AppointmentDtlsEntity> appointmentDtlsEntity;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="srd_dr_user_id_fk",referencedColumnName = "dmd_user_id")
	private List<ScribeRegEntity> scrbRegDtls;

}
