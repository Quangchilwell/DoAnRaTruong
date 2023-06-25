package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ClassDayDTO;

public interface ClassDayService {
	public List<ClassDayDTO> getAll();
	
	public List<ClassDayDTO> getByidClass(int idClass);
	
	public List<ClassDayDTO> getClassDaysWereCompleted(int idClass);

	public List<ClassDayDTO> getClassDaysWerePostPone(int idClass);
	
	public void add(ClassDayDTO classDayDTO);
	
	public void update(ClassDayDTO classDayDTO);
	
	public void delete(int id);
	
	public ClassDayDTO getByID(int id);
}
