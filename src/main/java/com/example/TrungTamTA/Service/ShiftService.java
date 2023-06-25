package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ShiftDTO;

public interface ShiftService {
	public List<ShiftDTO> getAll();
	
	public void add(ShiftDTO shiftDTO);
	
	public void update(ShiftDTO shiftDTO);
	
	public void delete(int id);
	
	public ShiftDTO getByID(int id);
}
