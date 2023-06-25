package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Student;

public interface StudentDao {
	public List<Student> getAll();
	
	public void add(Student student);
	
	public void update(Student student);
	
	public void delete(Student student);
	
	public Student getByID(int id);
}
