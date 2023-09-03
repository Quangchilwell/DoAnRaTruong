package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.TeacherHistory;

public interface TeacherHistoryRepository extends JpaRepository<TeacherHistory, Integer>{

	public TeacherHistory findByid(int id);
	
	public List<TeacherHistory> findByidTeacher(int idStudent);
	
	public List<TeacherHistory> findByidClass(int idClass);
}
