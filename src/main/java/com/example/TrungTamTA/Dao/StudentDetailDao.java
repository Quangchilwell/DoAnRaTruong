package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.StudentDetail;

public interface StudentDetailDao {
	public List<StudentDetail> getAll();
	
	public List<StudentDetail> getByidStudent(int idStudent);
	
	public List<StudentDetail> getByidClass(int idClass);
	
	public void add(StudentDetail studentDetail);
	
	public void update(StudentDetail studentDetail);
	
	public void delete(StudentDetail studentDetail);
	
	public StudentDetail getByID(int id);
}
