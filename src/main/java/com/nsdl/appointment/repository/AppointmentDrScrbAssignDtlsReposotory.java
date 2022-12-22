package com.nsdl.appointment.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.ApptDrScrbAssignDtl;

@Repository
@Transactional
public interface AppointmentDrScrbAssignDtlsReposotory extends JpaRepository<ApptDrScrbAssignDtl, Long> {
	
	@Query("FROM ApptDrScrbAssignDtl a WHERE a.drUserIdFk = ?1 AND a.date = ?2 and isactive=true")
	ApptDrScrbAssignDtl findByDrUserIdFkAndDate(String doctorId, Date date);
}
