package com.nsdl.appointment.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.ConsultationHistoryEntity;

@Repository
@Transactional
public interface ConsultationHistoryRepo extends JpaRepository<ConsultationHistoryEntity, String>{

	@Query(value ="SELECT * FROM appointment.consultation_history where consultation_history.ch_usr_id=:drUserId and consultation_history.ch_flag =:flag", nativeQuery = true)
	public List<ConsultationHistoryEntity> getFlagStatus(@Param("drUserId") String drUserId,@Param("flag") String flag);

	
	@Modifying
	@Query(value = "update appointment.consultation_history set ch_appt_id =:appointID, ch_created_tmpstmp =:currTime, ch_slot_time =:slotTime, ch_patient_id =:patientId, ch_flag =:oldFlag  where ch_session_id = :sessionId", nativeQuery = true)
	public void updateConsultationHistory(@Param("sessionId") String sessionId,@Param("appointID") String appointID,@Param("slotTime") String slotTime,
			@Param("currTime") LocalDateTime currTime,@Param("patientId") String patientId,@Param("oldFlag") String oldFlag);

	
	

}
