package com.example.TrungTamTA.Service;

import java.util.List;


import com.example.TrungTamTA.Model.AttendanceDTO;

public interface AttendanceService {
	
	public List<AttendanceDTO> getAll();
	
	public List<AttendanceDTO> getByIdStudent(int idStudent);
	
	public List<AttendanceDTO> getByIdClass(int idClass);
	
	public List<AttendanceDTO> getByStatus(int status);
	
	public void add(AttendanceDTO dto);
	
	public void update(AttendanceDTO dto);
	
	public void delete(int id);
	
	public AttendanceDTO getById(int id);
}
