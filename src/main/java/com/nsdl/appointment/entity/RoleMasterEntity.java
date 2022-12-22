package com.nsdl.appointment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role_mstr", schema = "usrmgmt")
public class RoleMasterEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID =  -8541947597557593690L;
	
	@Column(name ="rm_role",  nullable = false)
	private int ID;	
	
	@Id
	@Column(name ="rm_role_name_pk", length = 100)
	private String roleName;
	
}
