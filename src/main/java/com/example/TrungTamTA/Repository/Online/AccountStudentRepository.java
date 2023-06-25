package com.example.TrungTamTA.Repository.Online;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.AccountStudent;

public interface AccountStudentRepository extends JpaRepository<AccountStudent, Integer>{
	public AccountStudent findByid(int id);
	
	public AccountStudent findByemail(String email);
}
