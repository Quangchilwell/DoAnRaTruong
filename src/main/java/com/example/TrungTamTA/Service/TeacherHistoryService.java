package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.TeacherHistoryDTO;

public interface TeacherHistoryService {
	public List<TeacherHistoryDTO> getAll();
	
	public List<TeacherHistoryDTO> getByTeacherId(int teacherId);
	
	public void add(TeacherHistoryDTO dto);
	
	public void update(TeacherHistoryDTO dto);
	
	public void delete(TeacherHistoryDTO dto);
	
	public TeacherHistoryDTO getById(int id);
}
