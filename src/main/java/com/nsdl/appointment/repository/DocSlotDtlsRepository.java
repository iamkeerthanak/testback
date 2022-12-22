package com.nsdl.appointment.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.DocSlotDtlsEntity;

@Repository
@Transactional
public interface DocSlotDtlsRepository extends JpaRepository<DocSlotDtlsEntity, Long> {

	@Query(value = "SELECT * FROM appointment.dr_slot_dtls s WHERE s.dsd_dr_user_id_fk = ?1 and s.dsd_isactive = true and s.dsd_slot_date >= ?2 and s.dsd_slot_date <= ?3  and NOT EXISTS ( select 1 from appointment.appt_dtls a where s.dsd_dr_user_id_fk = a.ad_dr_user_id_fk and s.dsd_slot = a.ad_appt_slot_fk and s.dsd_Slot_date = a.ad_appt_date_fk and a.ad_appt_status in ('C','S','R','SCHEDULEWP')) order by s.dsd_Slot_date,s.dsd_slot asc", nativeQuery = true)
	public List<DocSlotDtlsEntity> findAllDocSlotDtls(String docId, Date startDate, Date endDate);

	@Query(value = "SELECT * FROM appointment.dr_slot_dtls s WHERE s.dsd_dr_user_id_fk = ?1 and s.dsd_isactive = true  and  NOT EXISTS ( select 1 from appointment.appt_dtls a where s.dsd_dr_user_id_fk = a.ad_dr_user_id_fk and s.dsd_slot = a.ad_appt_slot_fk and s.dsd_Slot_date = a.ad_appt_date_fk and a.ad_appt_status in ('C','S','R','SCHEDULEWP')) order by s.dsd_Slot_date,s.dsd_slot asc", nativeQuery = true)
	public List<DocSlotDtlsEntity> findAllDocSlotDtlsWithoutDate(String docId);

	@Query(value = "SELECT * FROM appointment.dr_slot_dtls s WHERE s.dsd_dr_user_id_fk = ?1 and s.dsd_isactive = true and s.dsd_slot_date = ?2  and NOT EXISTS ( select 1 from appointment.appt_dtls a where s.dsd_dr_user_id_fk = a.ad_dr_user_id_fk and s.dsd_slot = a.ad_appt_slot_fk and s.dsd_Slot_date = a.ad_appt_date_fk and a.ad_appt_status in ('C','S','R')) order by s.dsd_Slot_date,s.dsd_slot asc", nativeQuery = true)
	public List<DocSlotDtlsEntity> findDocSlotDtlsByDate(String docId, Date date);//C,S,R !X
	
	@Query(value = "SELECT * FROM appointment.dr_slot_dtls s WHERE s.dsd_dr_user_id_fk = ?1 and s.dsd_isactive = true and s.dsd_slot_date = ?2  and NOT EXISTS ( select 1 from appointment.appt_dtls a where s.dsd_dr_user_id_fk = a.ad_dr_user_id_fk and s.dsd_slot = a.ad_appt_slot_fk and s.dsd_Slot_date = a.ad_appt_date_fk and a.ad_appt_status in ('C','S','R','SCHEDULEWP')) order by s.dsd_Slot_date,s.dsd_slot asc", nativeQuery = true)
	public List<DocSlotDtlsEntity> findDocSlotDtlsByCurrentDate(String docId, LocalDate date);//C,S,R !X

}
