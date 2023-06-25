package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.TutorDTO;

public interface TutorService {
	public List<TutorDTO> getAll();
	
	public void add(TutorDTO tutorDTO);
	
	public void update(TutorDTO tutorDTO);
	
	public void delete(int id);
	
	public TutorDTO getByID(int id);
}
