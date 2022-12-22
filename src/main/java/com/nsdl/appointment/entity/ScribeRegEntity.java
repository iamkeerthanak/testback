package com.nsdl.appointment.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
@Entity
@Table(schema = "registration", name = "scrb_reg_dtls")
public class ScribeRegEntity {

	@Id
	@Column(name = "srd_id_pk")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer scrbId;
	
	@Column(name = "srd_scrb_name", length = 100, nullable = false, updatable = false)
	private String scrbFullName;
	
	@Column(name = "srd_mobile_no", length = 10, nullable = false, unique = true)
	private Long scrbMobNo;
	
	@Column(name = "srd_email", length = 50)
	private String scrbEmail;
	
	@Column(name = "srd_user_id", length = 50, nullable = false, unique = true)
	private String scrbUserID;
	
	@Column(name = "srd_dr_user_id_fk", length = 50, nullable = false)
	private String scrbdrUserIDfk;
	
	@Column(name = "srd_password", length = 100, nullable = false)
	private String scrbPassword;
	
	@Column(name = "srd_address1", length = 100)
	private String scrbAdd1;
	
	@Column(name = "srd_address2", length = 100)
	private String scrbAdd2;
	
	@Column(name = "srd_address3", length = 100)
	private String scrbAdd3;
	
	@Column(name = "srd_address4", length = 100)
	private String scrbAdd4;
	
	@Column(name = "srd_isactive", length = 1, nullable = false)
	private String scrbisActive;
	
	@Column(name = "srd_photo_path")
	private String profilePhotoPath;
	
	@Column(name = "srd_created_by", length = 50)
	private String scrbCreadtedBy;
	
	@Column(name = "srd_created_tmstmp")
	private Timestamp scrbCreatedTime;
	
	@Column(name = "srd_modified_by", length = 50)
	private String scrbModifiedBy;
	
	@Column(name = "srd_modified_tmstmp")
	private Timestamp scrbModifiedTime;
	
	@Column(name = "srd_opti_version")
	private String scrbOptiVersion;
	
	@Column(name = "srd_is_default_scribe", length = 1)
	private String isDefaultScribe;
	
	
	public String getIsDefaultScribe() {
		return isDefaultScribe;
	}

	public void setIsDefaultScribe(String isDefaultScribe) {
		this.isDefaultScribe = isDefaultScribe;
	}

	public Integer getScrbId() {
		return scrbId;
	}

	public void setScrbId(Integer scrbId) {
		this.scrbId = scrbId;
	}

	public String getScrbFullName() {
		return scrbFullName;
	}

	public void setScrbFullName(String scrbFullName) {
		this.scrbFullName = scrbFullName;
	}

	public Long getScrbMobNo() {
		return scrbMobNo;
	}

	public void setScrbMobNo(Long scrbMobNo) {
		this.scrbMobNo = scrbMobNo;
	}

	public String getScrbEmail() {
		return scrbEmail;
	}

	public void setScrbEmail(String scrbEmail) {
		this.scrbEmail = scrbEmail;
	}

	public String getScrbUserID() {
		return scrbUserID;
	}

	public void setScrbUserID(String scrbUserID) {
		this.scrbUserID = scrbUserID;
	}

	public String getScrbdrUserIDfk() {
		return scrbdrUserIDfk;
	}

	public void setScrbdrUserIDfk(String scrbdrUserIDfk) {
		this.scrbdrUserIDfk = scrbdrUserIDfk;
	}

	public String getScrbPassword() {
		return scrbPassword;
	}

	public void setScrbPassword(String scrbPassword) {
		this.scrbPassword = scrbPassword;
	}

	public String getScrbAdd1() {
		return scrbAdd1;
	}

	public void setScrbAdd1(String scrbAdd1) {
		this.scrbAdd1 = scrbAdd1;
	}

	public String getScrbAdd2() {
		return scrbAdd2;
	}

	public void setScrbAdd2(String scrbAdd2) {
		this.scrbAdd2 = scrbAdd2;
	}

	public String getScrbAdd3() {
		return scrbAdd3;
	}

	public void setScrbAdd3(String scrbAdd3) {
		this.scrbAdd3 = scrbAdd3;
	}

	public String getScrbAdd4() {
		return scrbAdd4;
	}

	public void setScrbAdd4(String scrbAdd4) {
		this.scrbAdd4 = scrbAdd4;
	}

	public String getScrbisActive() {
		return scrbisActive;
	}

	public void setScrbisActive(String scrbisActive) {
		this.scrbisActive = scrbisActive;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getScrbCreadtedBy() {
		return scrbCreadtedBy;
	}

	public void setScrbCreadtedBy(String scrbCreadtedBy) {
		this.scrbCreadtedBy = scrbCreadtedBy;
	}

	public Timestamp getScrbCreatedTime() {
		return scrbCreatedTime;
	}

	public void setScrbCreatedTime(Timestamp scrbCreatedTime) {
		this.scrbCreatedTime = scrbCreatedTime;
	}

	public String getScrbModifiedBy() {
		return scrbModifiedBy;
	}

	public void setScrbModifiedBy(String scrbModifiedBy) {
		this.scrbModifiedBy = scrbModifiedBy;
	}

	public Timestamp getScrbModifiedTime() {
		return scrbModifiedTime;
	}

	public void setScrbModifiedTime(Timestamp scrbModifiedTime) {
		this.scrbModifiedTime = scrbModifiedTime;
	}

	public String getScrbOptiVersion() {
		return scrbOptiVersion;
	}

	public void setScrbOptiVersion(String scrbOptiVersion) {
		this.scrbOptiVersion = scrbOptiVersion;
	}

}
