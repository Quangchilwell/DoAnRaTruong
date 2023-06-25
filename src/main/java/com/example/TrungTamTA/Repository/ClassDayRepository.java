package com.example.TrungTamTA.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.ClassDay;

public interface ClassDayRepository extends JpaRepository<ClassDay, Integer>{
	public ClassDay findByid(int id);
	
	public List<ClassDay> findByidClassOpening(int idClass);
	
	@Query(value = "SELECT cd FROM ClassDay cd\r\n"
			+ "WHERE cd.idClassOpening = ?1 AND cd.day = ?2")
	public ClassDay checkClassCompleted(int idClass, Date date);
	
	@Query(value = "SELECT cd FROM ClassDay cd\r\n"
			+ "WHERE cd.idClassOpening = ?1 AND cd.status = 1")
	public List<ClassDay> getClassDaysWereCompleted(int idClass);
	
	@Query(value = "SELECT cd FROM ClassDay cd\r\n"
			+ "WHERE cd.idClassOpening = ?1 AND cd.status = -1")
	public List<ClassDay> getClassDaysWerePostPone(int idClass);
}
