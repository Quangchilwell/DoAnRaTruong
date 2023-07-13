package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Entity.Attendance;
import com.example.TrungTamTA.Model.AttendanceDTO;
import com.example.TrungTamTA.Repository.AttendanceRepository;
import com.example.TrungTamTA.Service.AttendanceService;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.LessonService;
import com.example.TrungTamTA.Service.StudentService;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	
	@Autowired AttendanceRepository repository;
	@Autowired StudentService studentService;
	@Autowired ClassOpeningService classService;
	@Autowired LessonService lessonService;
	
	private void getInfo(Attendance attendance, AttendanceDTO dto) {
		dto.setId(attendance.getId());
		dto.setIdClass(attendance.getIdClass());
		dto.setIdStudent(attendance.getIdStudent());
		dto.setStatus(attendance.getStatus());
		dto.setAttendanceDate(String.valueOf(attendance.getAttendanceDate()));
		dto.setClassDTO(classService.getByID(attendance.getIdClass()));
		dto.setLessonDTO(lessonService.getByID(attendance.getIdLesson()));
		dto.setStudentDTO(studentService.getByID(attendance.getIdStudent()));
	}
	
	private void setInfo(Attendance attendance, AttendanceDTO dto) {
		attendance.setIdClass(dto.getClassDTO().getId());
		attendance.setIdLesson(dto.getLessonDTO().getId());
		attendance.setIdStudent(dto.getStudentDTO().getId());
		attendance.setStatus(dto.getStatus());
		attendance.setAttendanceDate(Date.valueOf(LocalDate.now()));
	}
	
	@Override
	public List<AttendanceDTO> getAll() {
		List<Attendance> attendances = repository.findAll();
		List<AttendanceDTO> dtos = new ArrayList<AttendanceDTO>();
		
		for(Attendance attendance: attendances) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<AttendanceDTO> getByIdStudent(int idStudent) {
		List<Attendance> attendances = repository.findByidStudent(idStudent);
		List<AttendanceDTO> dtos = new ArrayList<AttendanceDTO>();
		
		for(Attendance attendance: attendances) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<AttendanceDTO> getByIdClass(int idClass) {
		List<Attendance> attendances = repository.findByidClass(idClass);
		List<AttendanceDTO> dtos = new ArrayList<AttendanceDTO>();
		
		for(Attendance attendance: attendances) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	@Override
	public List<AttendanceDTO> getByIdLesson(int idLesson) {
		List<Attendance> attendances = repository.findByidLesson(idLesson);
		List<AttendanceDTO> dtos = new ArrayList<AttendanceDTO>();
		
		for(Attendance attendance: attendances) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<AttendanceDTO> getByStatus(int status) {
		List<Attendance> attendances = repository.findBystatus(status);
		List<AttendanceDTO> dtos = new ArrayList<AttendanceDTO>();
		
		for(Attendance attendance: attendances) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(AttendanceDTO dto) {
		Attendance attendance = new Attendance();
		setInfo(attendance, dto);
		repository.save(attendance);
	}

	@Override
	public void update(AttendanceDTO dto) {
		Attendance attendance = repository.findByid(dto.getId());
		if(attendance != null) {
			setInfo(attendance, dto);
			repository.save(attendance);
		}
	}

	@Override
	public void delete(int id) {
		Attendance attendance = repository.findByid(id);
		if(attendance != null) {
			repository.delete(attendance);
		}
	}

	@Override
	public AttendanceDTO getById(int id) {
		Attendance attendance = repository.findByid(id);
		if(attendance != null) {
			AttendanceDTO dto = new AttendanceDTO();
			getInfo(attendance, dto);
			return dto;
		}
		return null;
	}
}
