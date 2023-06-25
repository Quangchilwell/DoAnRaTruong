package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.CourseTypeDTO;

public interface CourseTypeService {
	public List<CourseTypeDTO> getAll();
	
	public void add(CourseTypeDTO courseTypeDTO);
	
	public void update(CourseTypeDTO courseTypeDTO);
	
	public void delete(int id);
	
	public CourseTypeDTO getByID(int id);
}
