package com.example.TrungTamTA.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.FutureStudentDao;
import com.example.TrungTamTA.Entity.FutureStudent;
import com.example.TrungTamTA.Model.FutureStudentDTO;
import com.example.TrungTamTA.Service.FutureStudentService;

@Service
@Transactional
public class FutureStudentServiceImpl implements FutureStudentService{

	@Autowired FutureStudentDao dao;
	
	private void getInfo(FutureStudentDTO dto, FutureStudent futureStudent) {
		dto.setId(futureStudent.getId());
		dto.setName(futureStudent.getName());
		dto.setPhone(futureStudent.getPhone());
		dto.setEmail(futureStudent.getEmail());
		dto.setAspiration(futureStudent.getAspiration());
		dto.setCreatedDate(futureStudent.getCreatedDate());
		dto.setUpdatedDate(futureStudent.getUpdatedDate());
		dto.setStatus(futureStudent.getStatus());
		dto.setSchedule(futureStudent.getSchedule());
	}
	
	private void setInfo(FutureStudentDTO dto, FutureStudent futureStudent) {
		futureStudent.setName(dto.getName());
		futureStudent.setEmail(dto.getEmail());
		futureStudent.setPhone(dto.getPhone());
		futureStudent.setAspiration(dto.getAspiration());
		futureStudent.setStatus(dto.getStatus());
		futureStudent.setSchedule(dto.getSchedule());
	}
	
	@Override
	public List<FutureStudentDTO> getAll() {
		List<FutureStudent> futureStudents = dao.getAll();
		List<FutureStudentDTO> futureStudentDTOs = new ArrayList<FutureStudentDTO>();
		
		for(FutureStudent futureStudent: futureStudents) {
			FutureStudentDTO dto = new FutureStudentDTO();
			getInfo(dto, futureStudent);
			futureStudentDTOs.add(dto);
		}
		return futureStudentDTOs;
	}
	
	@Override
	public List<FutureStudentDTO> getBystatus(int status) {
		List<FutureStudent> futureStudents = dao.getBystatus(status);
		List<FutureStudentDTO> futureStudentDTOs = new ArrayList<FutureStudentDTO>();
		
		for(FutureStudent futureStudent: futureStudents) {
			FutureStudentDTO dto = new FutureStudentDTO();
			getInfo(dto, futureStudent);
			futureStudentDTOs.add(dto);
		}
		return futureStudentDTOs;
	}


	@Override
	public void add(FutureStudentDTO futureStudentDTO) {
		FutureStudent futureStudent = new FutureStudent();
		setInfo(futureStudentDTO, futureStudent);
		dao.add(futureStudent);
	}

	@Override
	public void update(FutureStudentDTO futureStudentDTO) {
		FutureStudent futureStudent = dao.getByID(futureStudentDTO.getId());
		if(futureStudent != null) {
			setInfo(futureStudentDTO, futureStudent);
			dao.update(futureStudent);
		}
	}

	@Override
	public void delete(int id) {
		FutureStudent futureStudent = dao.getByID(id);
		if(futureStudent != null) {
			dao.delete(futureStudent);
		}
	}

	@Override
	public FutureStudentDTO getByID(int id) {
		FutureStudent futureStudent = dao.getByID(id);
		if(futureStudent != null) {
			FutureStudentDTO dto = new FutureStudentDTO();
			getInfo(dto, futureStudent);
			return dto;
		}
		return null;
	}
}
