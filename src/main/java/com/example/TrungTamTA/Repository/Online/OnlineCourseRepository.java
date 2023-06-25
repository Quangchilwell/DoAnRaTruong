package com.example.TrungTamTA.Repository.Online;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.OnlineCourse;

public interface OnlineCourseRepository extends JpaRepository<OnlineCourse, Integer>{
	public OnlineCourse findByid(int id);
	
	public List<OnlineCourse> findByidCourse(int idCourse);
	
	public List<OnlineCourse> findByidTeacher(int idTeacher);
	
	public List<OnlineCourse> findByidDiscount(int idDiscount);
}
