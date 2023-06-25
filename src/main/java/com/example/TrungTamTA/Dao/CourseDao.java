package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Course;
import com.example.TrungTamTA.Entity.RegisterCourse;

public interface CourseDao {
	public List<Course> getAll();
	
	public List<Course> getByIdType(int idType);
	
	public List<Course> getByIdForm(int idForm);
	
	public List<Course> getCoursesNotInCombo(int idCombo);
	
	public List<Course> getOfflineCourses();
	
	public List<Course> getOnlinelineCourses();
	
	public void add(Course course);
	
	public void update(Course course);
	
	public void delete(Course course);
	
	public Course getByID(int id);
	
}
