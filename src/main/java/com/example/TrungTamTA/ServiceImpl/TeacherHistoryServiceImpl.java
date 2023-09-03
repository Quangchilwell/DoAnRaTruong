package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Entity.TeacherHistory;
import com.example.TrungTamTA.Model.TeacherHistoryDTO;
import com.example.TrungTamTA.Repository.TeacherHistoryRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.TeacherHistoryService;
import com.example.TrungTamTA.Service.TeacherService;

@Service
public class TeacherHistoryServiceImpl implements TeacherHistoryService{

	@Autowired 
	private TeacherHistoryRepository repository;
	
	@Autowired
	private ClassOpeningService classOpeningService;
	
	@Autowired
	private TeacherService teacherService;
	
	private void getInfo(TeacherHistory history, TeacherHistoryDTO dto) {
		dto.setId(history.getId());
		dto.setIdClass(history.getIdClass());
		dto.setIdTeacher(history.getIdTeacher());
		dto.setCompletionDate(String.valueOf(history.getCompletionDate()));
		
		dto.setClassDTO(classOpeningService.getByID(history.getIdClass()));
		dto.setTeacherDTO(teacherService.getByID(history.getIdTeacher()));
	}
	
	private void setInfo(TeacherHistory history, TeacherHistoryDTO dto) {
		history.setIdClass(dto.getIdClass());
		history.setIdTeacher(dto.getIdTeacher());
		history.setCompletionDate(Date.valueOf(LocalDate.now()));
	}
	
	@Override
	public List<TeacherHistoryDTO> getAll() {
		List<TeacherHistoryDTO> dtos = new ArrayList<TeacherHistoryDTO>();
		List<TeacherHistory> histories = repository.findAll();
		
		for(TeacherHistory history: histories) {
			TeacherHistoryDTO dto = new TeacherHistoryDTO();
			getInfo(history, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<TeacherHistoryDTO> getByTeacherId(int teacherId) {
		List<TeacherHistoryDTO> dtos = new ArrayList<TeacherHistoryDTO>();
		List<TeacherHistory> histories = repository.findByidTeacher(teacherId);
		
		for(TeacherHistory history: histories) {
			TeacherHistoryDTO dto = new TeacherHistoryDTO();
			getInfo(history, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(TeacherHistoryDTO dto) {
		TeacherHistory history = new TeacherHistory();
		setInfo(history, dto);
		repository.save(history);
	}

	@Override
	public void update(TeacherHistoryDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TeacherHistoryDTO dto) {
		TeacherHistory history = repository.findByid(dto.getId());
		if(history != null) {
			repository.delete(history);
		}
	}

	@Override
	public TeacherHistoryDTO getById(int id) {
		TeacherHistory history = repository.findByid(id);
		if(history != null) {
			TeacherHistoryDTO dto = new TeacherHistoryDTO();
			getInfo(history, dto);
			return dto;
		}
		return null;
	}
	
}
