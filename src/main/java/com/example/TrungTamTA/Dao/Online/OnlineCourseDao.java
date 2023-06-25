package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.OnlineCourse;

public interface OnlineCourseDao {
	public List<OnlineCourse> getAll();
	
	public List<OnlineCourse> getByidCourse(int idCourse);
	
	public List<OnlineCourse> getByidTeacher(int idTeacher);
	
	public List<OnlineCourse> getByidDiscount(int idDiscount);
	
	public void add(OnlineCourse course);
	
	public void update(OnlineCourse course);
	
	public void delete(OnlineCourse course);
	
	public OnlineCourse getByID(int id);
}
