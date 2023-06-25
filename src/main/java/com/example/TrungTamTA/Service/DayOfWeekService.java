package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.DayOfWeekDTO;

public interface DayOfWeekService {
	public List<DayOfWeekDTO> getAll();
	
	public void add(DayOfWeekDTO dayOfWeekDTO);
	
	public void update(DayOfWeekDTO dayOfWeekDTO);
	
	public void delete(int id);
	
	public DayOfWeekDTO getById(int id);
}
