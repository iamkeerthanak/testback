package com.nsdl.appointment.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "usrmgmt",name = "usr_dtls")
public class LoginUserEntity {

	@Column(name = "ud_user_id_pk", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "ud_user_full_name",length = 100 ,  nullable = false)
	private String userFullName;
	
	@Column(name = "ud_user_id", length = 50 ,nullable = false)
	private String userId;

	@Column(name = "ud_password" ,length = 256 , nullable = false)
	private String password;

	@Column(name = "ud_mobile_no" , nullable = false)
	private Long mobile;
	
	@Column(name = "ud_email" ,length = 100)
	private String email;
	
	@Column(name = "ud_smc_number" , length = 100)
	private String smcNumber;
	
	@Column(name = "ud_mci_number" , length = 100)
	private String mciNumber;
	
	@Column(name = "ud_user_type" , length = 50 , nullable = false)
	private String userType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ud_role_id_fk",referencedColumnName = "rm_role_name_pk",nullable = false )
	private RoleMasterEntity roleEntity;
	
	@Column(name = "ud_islock_flg")
	private Boolean isLock = false;
	
	@Column(name = "ud_fail_attempt_count")
	private Long failAttemptCount;
	
	@Column(name = "ud_fail_attempt_tmstmp")
	private LocalDateTime failAttemptTime;
	
	@Column(name = "ud_pwd_expiry_tmstmp")
	private LocalDateTime pwdExpiryTime;
	
	@Column(name = "ud_session_id")
	private String sessionId;

	@Column(name = "ud_ischange_pwd" )
	private Boolean isPwdChange = false;

	@Column(name = "ud_logged_in_flg")
	private Boolean isLoggedIn = false;
	
	@Column(name = "ud_isactive_flg" )
	private Boolean isActive = false;
	
	@Column(name = "ud_de_reg_reason")
	private String deRegisterReason;
	
	@Column(name = "ud_created_by")
	private String createdBy;

	@Column(name = "ud_created_tmstmp")
	private LocalDateTime createdTime;

	@Column(name = "ud_modified_by")
	private String modifiedBy;

	@Column(name = "ud_modified_tmstmp")
	private LocalDateTime modifiedTime;
	
	@Version
	@Column(name = "ud_opti_version")
	private Long version;
}
