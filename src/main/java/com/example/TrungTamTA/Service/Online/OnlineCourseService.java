package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.OnlineCourse;
import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;

public interface OnlineCourseService {
	public List<OnlineCourseDTO> getAll();
	
	public List<OnlineCourseDTO> getByidCourse(int idCourse);
	
	public List<OnlineCourseDTO> getByidTeacher(int idTeacher);
	
	public List<OnlineCourseDTO> getByidDiscount(int idDiscount);
	
	public void add(OnlineCourseDTO courseDTO);
	
	public void update(OnlineCourseDTO courseDTO);
	
	public void delete(int id);
	
	public OnlineCourseDTO getByID(int id);
}
