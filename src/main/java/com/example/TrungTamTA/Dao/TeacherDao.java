package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Teacher;

public interface TeacherDao {
	public List<Teacher> getAll();
	
	public List<Teacher> getTeacherForOnlineCourse(int idCourse);
	
	public void add(Teacher teacher);
	
	public void update(Teacher teacher);
	
	public void delete(Teacher teacher);
	
	public Teacher getByID(int id);
}
