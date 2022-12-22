package com.nsdl.appointment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsdl.appointment.entity.AssistantRegDtlsEntity;


public interface AssistantRegRepo extends JpaRepository<AssistantRegDtlsEntity, String> {

    public Optional<AssistantRegDtlsEntity> findByAssistantUserId (String assistUserID);
	
	public Optional<AssistantRegDtlsEntity> findByMobileNo(Long assistMobNo);
	
	public Optional<AssistantRegDtlsEntity> findByEmail(String assistEmail);
	
	public List<AssistantRegDtlsEntity> findBydrUserIdFk(String drUserIDfk);

	
	
}
