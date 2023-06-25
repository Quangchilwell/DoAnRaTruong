package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ComboDetailDTO;

public interface ComboDetailService {
	public List<ComboDetailDTO> getAll();
	
	public List<ComboDetailDTO> getByidCourse(int idCourse);
	
	public List<ComboDetailDTO> getByidCombo(int idCombo);
	
	public void add(ComboDetailDTO comboDetailDTO);
	
	public void update(ComboDetailDTO comboDetailDTO);
	
	public void delete(int id);
	
	public ComboDetailDTO getByID(int id);
}
