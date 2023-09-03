package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Entity.StudentDetailInCompletedClass;
import com.example.TrungTamTA.Model.StudentDetailInCompletedClassDTO;
import com.example.TrungTamTA.Repository.StudentDetailInCompletedClassRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.StudentDetailInCompletedClassService;
import com.example.TrungTamTA.Service.StudentService;

@Service
public class StudentDetailInCompletedClassServiceImpl implements StudentDetailInCompletedClassService{

	@Autowired StudentDetailInCompletedClassRepository repository;
	
	@Autowired StudentService studentService;
	
	@Autowired ClassOpeningService classOpeningService;
	
	private void getInfo(StudentDetailInCompletedClassDTO dto, StudentDetailInCompletedClass stu) {
		dto.setId(stu.getId());
		dto.setClassOpeningDTO(classOpeningService.getByID(stu.getIdClass()));
		dto.setStudentDTO(studentService.getByID(stu.getIdStudent()));
		dto.setCompletedDate(String.valueOf(stu.getCompletedDate()));
		dto.setIsPassed(stu.getIsPassed());
	}
	
	
	private void setInfo(StudentDetailInCompletedClassDTO dto, StudentDetailInCompletedClass stu) {
		stu.setIdClass(dto.getClassOpeningDTO().getId());
		stu.setIdStudent(dto.getStudentDTO().getId());
		stu.setCompletedDate(Date.valueOf(LocalDate.now()));
		stu.setIsPassed(dto.getIsPassed());
	}
	
	@Override
	public List<StudentDetailInCompletedClassDTO> getAll() {
		List<StudentDetailInCompletedClass> students = repository.findAll();
		List<StudentDetailInCompletedClassDTO> dtos = new ArrayList<StudentDetailInCompletedClassDTO>();
		
		for(StudentDetailInCompletedClass stu: students) {
			StudentDetailInCompletedClassDTO dto = new StudentDetailInCompletedClassDTO();
			getInfo(dto, stu);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<StudentDetailInCompletedClassDTO> getByIdStudent(int idStudent) {
		List<StudentDetailInCompletedClass> students = repository.findByidStudent(idStudent);
		List<StudentDetailInCompletedClassDTO> dtos = new ArrayList<StudentDetailInCompletedClassDTO>();
		
		for(StudentDetailInCompletedClass stu: students) {
			StudentDetailInCompletedClassDTO dto = new StudentDetailInCompletedClassDTO();
			getInfo(dto, stu);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<StudentDetailInCompletedClassDTO> getByIdClass(int idClass) {
		List<StudentDetailInCompletedClass> students = repository.findByidClass(idClass);
		List<StudentDetailInCompletedClassDTO> dtos = new ArrayList<StudentDetailInCompletedClassDTO>();
		
		for(StudentDetailInCompletedClass stu: students) {
			StudentDetailInCompletedClassDTO dto = new StudentDetailInCompletedClassDTO();
			getInfo(dto, stu);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(StudentDetailInCompletedClassDTO dto) {
		try {
			StudentDetailInCompletedClass stu = new StudentDetailInCompletedClass();
			setInfo(dto, stu);
			repository.save(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(StudentDetailInCompletedClassDTO dto) {
		try {
			StudentDetailInCompletedClass stu = repository.findByid(dto.getId());
			if(stu != null) {
				setInfo(dto, stu);
				repository.save(stu);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			StudentDetailInCompletedClass stu = repository.findByid(id);
			if(stu != null) {
				repository.delete(stu);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
