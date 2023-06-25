package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.StudentDTO;

public interface StudentService {
	public List<StudentDTO> getAll();
	
	public void add(StudentDTO studentDTO);
	
	public void update(StudentDTO studentDTO);
	
	public void delete(int id);
	
	public StudentDTO getByID(int id);
}
