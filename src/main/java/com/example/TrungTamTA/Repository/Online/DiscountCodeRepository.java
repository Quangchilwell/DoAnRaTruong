package com.example.TrungTamTA.Repository.Online;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.DiscountCode;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer>{
	public DiscountCode findByid(int id);
}
