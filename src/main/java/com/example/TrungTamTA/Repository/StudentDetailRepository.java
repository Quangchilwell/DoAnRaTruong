package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.StudentDetail;

public interface StudentDetailRepository extends JpaRepository<StudentDetail, Integer>{
	public StudentDetail findByid(int id);
	
	public List<StudentDetail> findByidStudent(int idStudent);
	
	public List<StudentDetail> findByidClass(int idClass);
}
