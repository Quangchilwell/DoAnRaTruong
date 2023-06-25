package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.ClassDetail;

public interface ClassDetailRepository extends JpaRepository<ClassDetail, Integer>{
	public ClassDetail findByid(int id);

	public List<ClassDetail> findByidClassOpening(int idClassOpening);
	
	public List<ClassDetail> findByidDayOfWeek(int idDayOfWeek);
	
}
