package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ClassRoomDTO;

public interface ClassRoomService {
	public List<ClassRoomDTO> getAll();
	
	public void add(ClassRoomDTO classRoomDTO);
	
	public void update(ClassRoomDTO classRoomDTO);
	
	public void delete(int id);
	
	public ClassRoomDTO getByID(int id);
	
	public List<ClassRoomDTO> getByIdType(int idType);
}
