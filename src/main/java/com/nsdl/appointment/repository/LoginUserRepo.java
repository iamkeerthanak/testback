package com.nsdl.appointment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsdl.appointment.entity.LoginUserEntity;

@Repository
@Transactional
public interface LoginUserRepo extends JpaRepository<LoginUserEntity, Integer> {

	public LoginUserEntity findByUserId(String userId);
	
}
