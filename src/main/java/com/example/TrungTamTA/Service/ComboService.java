package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ComboDTO;

public interface ComboService {
	public List<ComboDTO> getAll();
	
	public void add(ComboDTO comboDTO);
	
	public void update(ComboDTO comboDTO);
	
	public void delete(int id);
	
	public ComboDTO getByID(int id);
}
