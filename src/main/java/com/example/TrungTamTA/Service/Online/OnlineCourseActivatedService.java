package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Model.Online.OnlineCourseActivatedDTO;

public interface OnlineCourseActivatedService {
	public List<OnlineCourseActivatedDTO> getAll();
	
	public List<OnlineCourseActivatedDTO> getByidAccount(int idAccount);
	
	public List<OnlineCourseActivatedDTO> getByidOnlineCourse(int idOnlineCourse);
	
	public void add(OnlineCourseActivatedDTO activatedDTO);
	
	public void update(OnlineCourseActivatedDTO activatedDTO);
	
	public void delete(int id);
	
	public OnlineCourseActivatedDTO getByID(int id);
}
