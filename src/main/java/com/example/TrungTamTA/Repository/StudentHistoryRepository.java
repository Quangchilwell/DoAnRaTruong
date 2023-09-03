package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.StudentHistory;

public interface StudentHistoryRepository extends JpaRepository<StudentHistory, Integer>{
	public StudentHistory findByid(int id);
	
	public List<StudentHistory> findByidStudent(int idStudent);
	
	public List<StudentHistory> findByidClass(int idClass);
	
}
