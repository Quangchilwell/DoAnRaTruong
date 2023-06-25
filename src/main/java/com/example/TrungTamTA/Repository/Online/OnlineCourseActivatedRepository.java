package com.example.TrungTamTA.Repository.Online;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.OnlineCourseActivated;

public interface OnlineCourseActivatedRepository extends JpaRepository<OnlineCourseActivated, Integer>{
	public OnlineCourseActivated findByid(int id);
	
	public List<OnlineCourseActivated> findByidAccount(int idAccount);
	
	public List<OnlineCourseActivated> findByidOnlineCourse(int idOnlineCourse);
}
