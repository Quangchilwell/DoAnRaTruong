package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.TeachingFormDTO;

public interface TeachingFormService {
	public List<TeachingFormDTO> getAll();
	
	public void add(TeachingFormDTO teachingFormDTO);
	
	public void update(TeachingFormDTO teachingFormDTO);
	
	public void delete(int id);
	
	public TeachingFormDTO getByID(int id);
}
