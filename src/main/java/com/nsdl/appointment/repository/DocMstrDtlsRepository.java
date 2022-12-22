package com.nsdl.appointment.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.DocMstrDtlsEntity;

@Repository
@Transactional
public interface DocMstrDtlsRepository
		extends JpaRepository<DocMstrDtlsEntity, Long>, JpaSpecificationExecutor<DocMstrDtlsEntity> {

	public DocMstrDtlsEntity findByDmdDrName(String dmdDrName);
	
	//@Query("FROM DocMstrDtlsEntity a WHERE a.dmdUserId =:doctorUserId")
	public DocMstrDtlsEntity findByDmdUserId(String doctorUserId);

	public List<DocMstrDtlsEntity> findByDmdSpecialiazation(String speciality);

	@Query(value = "SELECT * FROM registration.dr_mstr_dtls s WHERE s.dmd_user_id in ( select dpmd_dr_user_id_fk from registration.dr_pt_map_dtls a where  a.dpmd_pt_user_id_fk= ?1 and a.dpmd_status='Y')", nativeQuery = true)
	public List<DocMstrDtlsEntity> getDrList(String userName);
	
	@Query(value = "SELECT * FROM registration.dr_mstr_dtls where dmd_user_id=?1",nativeQuery=true)
	public List<DocMstrDtlsEntity> getDoctorDetails(String userName);

}
