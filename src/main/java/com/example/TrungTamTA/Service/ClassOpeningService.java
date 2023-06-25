package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ClassOpeningDTO;

public interface ClassOpeningService {
	public List<ClassOpeningDTO> getAll();
	
	public List<ClassOpeningDTO> getByidTeacher(int idTeacher);
	
	public List<ClassOpeningDTO> getByidCourse(int idCourse);
	
	public List<ClassOpeningDTO> getByidClassRoom(int idRoom);
	
	public List<ClassOpeningDTO> getByidTutor(int idTutor);
	
	public List<ClassOpeningDTO> getActivateClasses();
	
	public void add(ClassOpeningDTO classOpeningDTO);
	
	public void update(ClassOpeningDTO classOpeningDTO);
	
	public void updateStatusOfClass(ClassOpeningDTO classOpeningDTO);
	
	public void delete(int id);
	
	public List<ClassOpeningDTO> getByStatus(int status);
	
	public ClassOpeningDTO getByID(int id);
	
	public List<ClassOpeningDTO> getDuplicateClassList(int idShift, int idClassOpening);
}
