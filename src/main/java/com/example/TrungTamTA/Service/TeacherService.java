package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.TeacherDTO;

public interface TeacherService {
	public List<TeacherDTO> getAll();
	
	public List<TeacherDTO> getTeacherForOnlineCourse(int idCourse);
	
	public void add(TeacherDTO teacher);
	
	public void update(TeacherDTO teacher);
	
	public void delete(int id);
	
	public TeacherDTO getByID(int id);
}
