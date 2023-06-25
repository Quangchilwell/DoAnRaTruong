package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.CourseDTO;

public interface CourseService {
	public List<CourseDTO> getAll();
	
	public List<CourseDTO> getByIdType(int idType);
	
	public List<CourseDTO> getByIdForm(int idForm);
	
	public List<CourseDTO> getCoursesNotInCombo(int idCombo);
	
	public List<CourseDTO> getOfflineCourses();
	
	public List<CourseDTO> getOnlineCourses();
	
	public void add(CourseDTO courseDTO);
	
	public void update(CourseDTO courseDTO);
	
	public void delete(int id);
	
	public CourseDTO getByID(int id);
}
