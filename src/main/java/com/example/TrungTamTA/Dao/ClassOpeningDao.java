package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.ClassOpening;

public interface ClassOpeningDao {
	
	public List<ClassOpening> getAll();
	
	public List<ClassOpening> getByidTeacher(int idTeacher);
	
	public List<ClassOpening> getByidCourse(int idCourse);
	
	public List<ClassOpening> getByidClassRoom(int idRoom);
	
	public List<ClassOpening> getByidTutor(int idTutor);
	
	public List<ClassOpening> getActivateClasses();
	
	public ClassOpening add(ClassOpening classOpening);
	
	public void update(ClassOpening classOpening);
	
	public void delete(ClassOpening classOpening);
	
	public ClassOpening getByID(int id);
	
	public List<ClassOpening> getBystatus(int status);
	
	public List<ClassOpening> getDuplicateClassList(int idShift, int idClassOpening);
}
