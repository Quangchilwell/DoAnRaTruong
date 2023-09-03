package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Entity.StudentHistory;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Model.StudentHistoryDTO;
import com.example.TrungTamTA.Repository.StudentHistoryRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.StudentHistoryService;
import com.example.TrungTamTA.Service.StudentService;

@Service
public class StudentHistoryServiceImpl implements StudentHistoryService{

	@Autowired 
	private StudentHistoryRepository historyRepository;
	
	@Autowired 
	private StudentService studentService;
	
	@Autowired ClassOpeningService classService;
	
	private void getInfo(StudentHistory history, StudentHistoryDTO dto) {
		dto.setId(history.getId());
		dto.setIdClass(history.getIdClass());
		dto.setIdStudent(history.getIdStudent());
		dto.setCompletionDate(String.valueOf(history.getCompletionDate()));
		dto.setCompletionStatus(history.getCompletionStatus());
		dto.setClassDTO(classService.getByID(history.getIdClass()));
		dto.setStudentDTO(studentService.getByID(history.getIdStudent()));
	}
	
	private void setInfo(StudentHistory history, StudentHistoryDTO dto) {
		history.setCompletionStatus(dto.getCompletionStatus());
		history.setCompletionDate(Date.valueOf(LocalDate.now()));
		history.setIdClass(dto.getIdClass());
		history.setIdStudent(dto.getIdStudent());
	}
	
	@Override
	public List<StudentHistoryDTO> getAll() {
		List<StudentHistory> histories = historyRepository.findAll();
		List<StudentHistoryDTO> dtos = new ArrayList<StudentHistoryDTO>();
		
		for(StudentHistory history: histories) {
			StudentHistoryDTO dto = new StudentHistoryDTO();
			getInfo(history, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<StudentHistoryDTO> getByStudentId(int studentId) {
		List<StudentHistory> histories = historyRepository.findByidStudent(studentId);
		List<StudentHistoryDTO> dtos = new ArrayList<StudentHistoryDTO>();
		
		for(StudentHistory history: histories) {
			StudentHistoryDTO dto = new StudentHistoryDTO();
			getInfo(history, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<StudentHistoryDTO> getByClassId(int classId) {
		List<StudentHistory> histories = historyRepository.findByidClass(classId);
		List<StudentHistoryDTO> dtos = new ArrayList<StudentHistoryDTO>();
		
		for(StudentHistory history: histories) {
			StudentHistoryDTO dto = new StudentHistoryDTO();
			getInfo(history, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(StudentHistoryDTO dto) {
		StudentHistory history = new StudentHistory();
		setInfo(history, dto);
		historyRepository.save(history);
	}

	@Override
	public void update(StudentHistoryDTO dto) {
		StudentHistory history = historyRepository.findByid(dto.getId());
		if(history != null) {
			setInfo(history, dto);
			historyRepository.save(history);
		}
	}

	@Override
	public void delete(int id) {
		StudentHistory history = historyRepository.findByid(id);
		if(history != null) {
			historyRepository.delete(history);
		}
	}

	@Override
	public StudentHistoryDTO getById(int id) {
		StudentHistory history = historyRepository.findByid(id);
		
		if(history != null) {
			StudentHistoryDTO dto = new StudentHistoryDTO();
			getInfo(history, dto);
			return dto;
		}
		
		return null;
	}
	
}
