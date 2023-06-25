package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.RegisterCourse;

public interface RegisterCourseDao {
	
	public List<RegisterCourse> getAll();
	
	public List<RegisterCourse> getByIdCourse(int idCourse);
	
	public List<RegisterCourse> getByIdStudent(int idStudent);
	
	public List<RegisterCourse> getByIdClassOpening(int idClass);
	
	public List<RegisterCourse> getByIdClassOpeningAndSoftDelete(int idClass, int softDelete);
	
	public List<RegisterCourse> getRegistersHaveNotClass();
	
	public List<RegisterCourse> getStudentsInClass(int idClass);
	
	public List<RegisterCourse> getRegistersCanOpenClass(int idCourse);
	
	public void add(RegisterCourse registerCourse);
	
	public void addMany(List<RegisterCourse> registerCourses);
	
	public void update(RegisterCourse registerCourse);
	
	public void delete(RegisterCourse registerCourse);
	
	public RegisterCourse getByID(int id);

}
