package com.nsdl.appointment.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Sudip Banerjee <br/> 
 * @version 1.0 <br/>
 * 
 * 
 * 
 * Defines an object to provide a wrapper for all common properties to TELE MEDICINE entities. <br/>
 * All the USER entities must extend from this class.<br/>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@Column(name = "cr_by", length = 50)
	private String createdBy;

	@Column(name = "cr_dtimes") 
	private LocalDateTime createdDateTime;

	@Column(name = "upd_by", length = 50)
	private String updatedBy;

	@Column(name = "upd_dtimes")
	private LocalDateTime updatedDateTime;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted;

	@Column(name = "del_dtimes")
	private LocalDateTime deletedDateTime;

}

