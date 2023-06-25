package com.example.TrungTamTA.ServiceImpl.Online;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.InvoiceOnlineCourseDao;
import com.example.TrungTamTA.Entity.Online.InvoiceOnlineCourse;
import com.example.TrungTamTA.Model.Online.AccountStudentDTO;
import com.example.TrungTamTA.Model.Online.InvoiceOnlineCourseDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;
import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.InvoiceOnlineCourseService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Service
public class InvoiceOnlineCourseServiceImpl implements InvoiceOnlineCourseService{

	@Autowired InvoiceOnlineCourseDao dao;
	@Autowired AccountStudentService acStudentService;
	@Autowired OnlineCourseService onlineCourseService;
	
	private void getInfo(InvoiceOnlineCourseDTO dto, InvoiceOnlineCourse inCourse) {
		OnlineCourseDTO courseDTO = onlineCourseService.getByID(inCourse.getIdOnlineCourse());
		AccountStudentDTO accountStudentDTO = acStudentService.getById(inCourse.getIdAccount());
		
		dto.setId(inCourse.getId());
		dto.setAccountStudentDTO(accountStudentDTO);
		dto.setOnlineCourseDTO(courseDTO);
		dto.setBuyDate(inCourse.getBuyDate());
		dto.setTotalPrice(inCourse.getTotalPrice());
		dto.setNote(inCourse.getNote());
	}
	
	private void setInfo(InvoiceOnlineCourseDTO dto, InvoiceOnlineCourse inCourse) {
		inCourse.setIdAccount(dto.getAccountStudentDTO().getId());
		inCourse.setIdOnlineCourse(dto.getOnlineCourseDTO().getId());
		inCourse.setTotalPrice(dto.getTotalPrice());
		inCourse.setNote(dto.getNote());
	}
	
	@Override
	public List<InvoiceOnlineCourseDTO> getAll() {
		List<InvoiceOnlineCourse> inCourses = dao.getAll();
		List<InvoiceOnlineCourseDTO> dtos = new ArrayList<InvoiceOnlineCourseDTO>();
		
		for(InvoiceOnlineCourse inCourse: inCourses) {
			InvoiceOnlineCourseDTO dto = new InvoiceOnlineCourseDTO();
			getInfo(dto, inCourse);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<InvoiceOnlineCourseDTO> getByidAccount(int idAccount) {
		List<InvoiceOnlineCourse> inCourses = dao.getByidAccount(idAccount);
		List<InvoiceOnlineCourseDTO> dtos = new ArrayList<InvoiceOnlineCourseDTO>();
		
		for(InvoiceOnlineCourse inCourse: inCourses) {
			InvoiceOnlineCourseDTO dto = new InvoiceOnlineCourseDTO();
			getInfo(dto, inCourse);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<InvoiceOnlineCourseDTO> getByidOnlineCourse(int idOnlineCourse) {
		List<InvoiceOnlineCourse> inCourses = dao.getByidOnlineCourse(idOnlineCourse);
		List<InvoiceOnlineCourseDTO> dtos = new ArrayList<InvoiceOnlineCourseDTO>();
		
		for(InvoiceOnlineCourse inCourse: inCourses) {
			InvoiceOnlineCourseDTO dto = new InvoiceOnlineCourseDTO();
			getInfo(dto, inCourse);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void add(InvoiceOnlineCourseDTO dto) {
		InvoiceOnlineCourse inCourse = new InvoiceOnlineCourse();
		setInfo(dto, inCourse);
		inCourse.setBuyDate(Timestamp.valueOf(LocalDateTime.now()));
		dao.add(inCourse);
	}

	@Override
	public void update(InvoiceOnlineCourseDTO dto) {
		InvoiceOnlineCourse inCourse = dao.getByID(dto.getId());
		if(inCourse != null) {
			setInfo(dto, inCourse);
			dao.update(inCourse);
		}
	}

	@Override
	public void delete(int id) {
		InvoiceOnlineCourse inCourse = dao.getByID(id);
		if(inCourse != null) {
			dao.delete(inCourse);
		}
	}

	@Override
	public InvoiceOnlineCourseDTO getByID(int id) {
		InvoiceOnlineCourse inCourse = dao.getByID(id);
		if(inCourse != null) {
			InvoiceOnlineCourseDTO dto = new InvoiceOnlineCourseDTO();
			getInfo(dto, inCourse);
			return dto; 
		}
		return null;
	}

}
