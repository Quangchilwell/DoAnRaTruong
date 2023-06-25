package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TrungTamTA.Dao.ClassDetailDao;
import com.example.TrungTamTA.Entity.ClassDetail;
import com.example.TrungTamTA.Model.ClassDetailDTO;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.DayOfWeekDTO;
import com.example.TrungTamTA.Service.ClassDetailService;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ClassRoomService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.DayOfWeekService;
import com.example.TrungTamTA.Service.ShiftService;
import com.example.TrungTamTA.Service.TeacherService;
import com.example.TrungTamTA.Service.TutorService;

@Service
@Transactional
@RequestMapping("/admin")

public class ClassDetailServiceImpl implements ClassDetailService{
	@Autowired ClassDetailDao dao;
	@Autowired ClassOpeningService classOpeningService;
	@Autowired DayOfWeekService dayOfWeekService;
	
	private void getInfo(ClassDetail classDetail, ClassDetailDTO classDetailDTO) {
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(classDetail.getIdClassOpening());
		DayOfWeekDTO dayOfWeekDTO = dayOfWeekService.getById(classDetail.getIdDayOfWeek());
		
		classDetailDTO.setId(classDetail.getId());
		classDetailDTO.setClassOpeningDTO(classOpeningDTO);
		classDetailDTO.setDayOfWeekDTO(dayOfWeekDTO);
		classDetailDTO.setLearningDate(String.valueOf(classDetail.getLearningDate()));
	}
	
	private void setInfo(ClassDetail classDetail, ClassDetailDTO classDetailDTO) {
		classDetail.setIdClassOpening(classDetailDTO.getClassOpeningDTO().getId());
		classDetail.setIdDayOfWeek(classDetailDTO.getDayOfWeekDTO().getId());
		classDetail.setLearningDate(Date.valueOf(LocalDate.now()));
	}
	
	@Override
	public List<ClassDetailDTO> getAll() {
		List<ClassDetailDTO> classDetailDTOs = new ArrayList<ClassDetailDTO>();
		List<ClassDetail> classDetails = dao.getAll();
		
		for(ClassDetail classDetail: classDetails) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			getInfo(classDetail, classDetailDTO);
			classDetailDTOs.add(classDetailDTO);
		}
		
		return classDetailDTOs;
	}
	
	@Override
	public List<ClassDetailDTO> getByidDayOfWeek(int idDayOfWeek) {
		List<ClassDetailDTO> classDetailDTOs = new ArrayList<ClassDetailDTO>();
		List<ClassDetail> classDetails = dao.getByidDayOfWeek(idDayOfWeek);
		
		for(ClassDetail classDetail: classDetails) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			getInfo(classDetail, classDetailDTO);
			classDetailDTOs.add(classDetailDTO);
		}
		
		return classDetailDTOs;
	}
	@Override
	public List<ClassDetailDTO> getByidClassOpening(int idClassOpening) {
		List<ClassDetailDTO> classDetailDTOs = new ArrayList<ClassDetailDTO>();
		List<ClassDetail> classDetails = dao.getByidClassOpening(idClassOpening);
		
		for(ClassDetail classDetail: classDetails) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			getInfo(classDetail, classDetailDTO);
			classDetailDTOs.add(classDetailDTO);
		}
		
		return classDetailDTOs;
	}
	
	@Override
	public void add(ClassDetailDTO classDetailDTO) {
		ClassDetail classDetail = new ClassDetail();
		setInfo(classDetail, classDetailDTO);
		dao.add(classDetail);
	}
	@Override
	public void update(ClassDetailDTO classDetailDTO) {
		ClassDetail classDetail = dao.getByID(classDetailDTO.getId());
		if(classDetail != null) {
			setInfo(classDetail, classDetailDTO);
			dao.update(classDetail);
		}
	}
	@Override
	public void delete(int id) {
		ClassDetail classDetail = dao.getByID(id);
		if(classDetail != null) {
			dao.delete(classDetail);
		}
	}
	
	@Override
	public ClassDetailDTO getByID(int id) {
		ClassDetail classDetail = dao.getByID(id);
		if(classDetail != null) {
			ClassDetailDTO classDetailDTO = new ClassDetailDTO();
			getInfo(classDetail, classDetailDTO);
			return classDetailDTO;
		}
		return null;
	}
	
	
}
