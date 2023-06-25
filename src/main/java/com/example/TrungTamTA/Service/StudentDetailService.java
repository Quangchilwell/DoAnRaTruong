package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.StudentDetailDTO;

public interface StudentDetailService {
	public List<StudentDetailDTO> getAll();
	
	public List<StudentDetailDTO> getByidStudent(int idStudent);
	
	public List<StudentDetailDTO> getByidClass(int idClass);
	
	public void add(StudentDetailDTO studentDetailDTO);
	
	public void update(StudentDetailDTO studentDetailDTO);
	
	public void delete(int id);
	
	public StudentDetailDTO getByID(int id);
}
