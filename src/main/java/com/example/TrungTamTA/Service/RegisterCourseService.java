package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.RegisterCourseDTO;

public interface RegisterCourseService {
	public List<RegisterCourseDTO> getAll();
	
	public List<RegisterCourseDTO> getByIdCourse(int idCourse);
	
	public List<RegisterCourseDTO> getByIdStudent(int idStudent);
	
	public List<RegisterCourseDTO> getByIdClassOpening(int idClass);
	
	public List<RegisterCourseDTO> getByIdClassOpeningAndSoftDelete(int idClass, int softDelete);
	
	public List<RegisterCourseDTO> getRegistersHaveNotClass();
	
	public List<RegisterCourseDTO> getStudentsInClass(int idClass);
	
	public List<RegisterCourseDTO> getRegistersCanOpenClass(int idCourse);
	
	public void add(RegisterCourseDTO registerCourseDTO);
	
	public void addMany(List<RegisterCourseDTO> registerCourseDTOs);
	
	public void update(RegisterCourseDTO registerCourseDTO);
	
	public void softDeleteRegister(RegisterCourseDTO registerCourseDTO);
	
	public void delete(int id);
	
	public RegisterCourseDTO getByID(int id);
}
