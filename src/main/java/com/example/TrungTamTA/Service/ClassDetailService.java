package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ClassDetailDTO;

public interface ClassDetailService {
	public List<ClassDetailDTO> getAll();
	
	public List<ClassDetailDTO> getByidDayOfWeek(int idDayOfWeek);
	
	public List<ClassDetailDTO> getByidClassOpening(int idClassOpening);
	
	public List<ClassDetailDTO> getAllByTeacherIdAndStatus0(int teacherId);
	
	public void add(ClassDetailDTO classDetailDTO);
	
	public void update(ClassDetailDTO classDetailDTO);
	
	public void delete(int id);
	
	public ClassDetailDTO getByID(int id);
}
