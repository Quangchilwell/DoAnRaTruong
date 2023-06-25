package com.example.TrungTamTA.Repository.Online;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.DetailAddMoney;

public interface DetailAddMoneyRepository extends JpaRepository<DetailAddMoney, Integer>{
	public DetailAddMoney findByid(int id);
	
	public List<DetailAddMoney> findByidAccount(int idAccount);
}
