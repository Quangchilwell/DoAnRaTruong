package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.StudentDetailInCompletedClassDTO;

public interface StudentDetailInCompletedClassService {
	
	public List<StudentDetailInCompletedClassDTO> getAll();
	
	public List<StudentDetailInCompletedClassDTO> getByIdStudent(int idStudent);
	
	public List<StudentDetailInCompletedClassDTO> getByIdClass(int idClass);
	
	public void add(StudentDetailInCompletedClassDTO dto);
	
	public void update(StudentDetailInCompletedClassDTO dto);
	
	public void delete(int id);
}
