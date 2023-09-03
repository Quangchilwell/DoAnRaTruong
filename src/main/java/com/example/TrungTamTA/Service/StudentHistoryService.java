package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.StudentHistoryDTO;

public interface StudentHistoryService {
	
	public List<StudentHistoryDTO> getAll();
	
	public List<StudentHistoryDTO> getByStudentId(int studentId);
	
	public List<StudentHistoryDTO> getByClassId(int classId);
	
	public void add(StudentHistoryDTO dto);
	
	public void update(StudentHistoryDTO dto);
	
	public void delete(int id);
	
	public StudentHistoryDTO getById(int id);
}
