package com.example.TrungTamTA.ServiceImpl.Online;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.OnlineCourseDao;
import com.example.TrungTamTA.Entity.Online.OnlineCourse;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Model.Online.DiscountCodeDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.TeacherService;
import com.example.TrungTamTA.Service.Online.DiscountCodeService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Service
public class OnlineCourseServiceImpl implements OnlineCourseService{

	@Autowired OnlineCourseDao dao;
	@Autowired CourseService courseService;
	@Autowired TeacherService teacherService;
	@Autowired DiscountCodeService discountCodeService;
	
	private void getInfo(OnlineCourseDTO dto, OnlineCourse onlineCourse) {
		CourseDTO courseDTO = courseService.getByID(onlineCourse.getIdCourse());
		TeacherDTO teacherDTO = teacherService.getByID(onlineCourse.getIdTeacher());
		DiscountCodeDTO discountCodeDTO = discountCodeService.getByID(onlineCourse.getIdDiscount());
		dto.setId(onlineCourse.getId());
		
		if(discountCodeDTO != null) {
			dto.setDiscountCodeDTO(discountCodeDTO);
		}
		dto.setCourseDTO(courseDTO);
		dto.setTeacherDTO(teacherDTO);
		dto.setReducedPrice(onlineCourse.getReducedPrice());
		dto.setTotalRegisters(onlineCourse.getTotalRegisters());
		dto.setTotalVideos(onlineCourse.getTotalVideos());
		dto.setDescription(onlineCourse.getDescription());
		dto.setImage(onlineCourse.getImage());
		dto.setCreatedAt(onlineCourse.getCreatedAt());
		dto.setUpdatedAt(onlineCourse.getUpdatedAt());
	}

	private void setInfo(OnlineCourseDTO dto, OnlineCourse onlineCourse) {
		
		if(dto.getDiscountCodeDTO() != null) {
			onlineCourse.setIdDiscount(dto.getDiscountCodeDTO().getId());
		}
		onlineCourse.setIdCourse(dto.getCourseDTO().getId());
		onlineCourse.setIdTeacher(dto.getTeacherDTO().getId());
		onlineCourse.setReducedPrice(dto.getReducedPrice());
		onlineCourse.setTotalRegisters(dto.getTotalRegisters());
		onlineCourse.setTotalVideos(dto.getTotalVideos());
		onlineCourse.setDescription(dto.getDescription());
		onlineCourse.setImage(dto.getImage());
	}
	
	@Override
	public List<OnlineCourseDTO> getAll() {
		List<OnlineCourse> onlineCourses = dao.getAll();
		List<OnlineCourseDTO> dtos = new ArrayList<OnlineCourseDTO>();
		
		for(OnlineCourse onlineCourse: onlineCourses) {
			OnlineCourseDTO dto = new OnlineCourseDTO();
			getInfo(dto, onlineCourse); 
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void add(OnlineCourseDTO courseDTO) {
		OnlineCourse onlineCourse = new OnlineCourse();
		setInfo(courseDTO, onlineCourse);
		dao.add(onlineCourse);
	}

	@Override
	public void update(OnlineCourseDTO courseDTO) {
		OnlineCourse onlineCourse = dao.getByID(courseDTO.getId());
		if(onlineCourse != null) {
			setInfo(courseDTO, onlineCourse);
			onlineCourse.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			dao.update(onlineCourse);
		}
	}

	@Override
	public void delete(int id) {
		OnlineCourse onlineCourse = dao.getByID(id);
		if(onlineCourse != null) {
			dao.delete(onlineCourse);
		}
	}

	@Override
	public OnlineCourseDTO getByID(int id) {
		OnlineCourse onlineCourse = dao.getByID(id);
		if(onlineCourse != null) {
			OnlineCourseDTO dto = new OnlineCourseDTO();
			getInfo(dto, onlineCourse);
			return dto;
		}
		return null;
	}

	@Override
	public List<OnlineCourseDTO> getByidCourse(int idCourse) {
		List<OnlineCourse> onlineCourses = dao.getByidCourse(idCourse);
		List<OnlineCourseDTO> dtos = new ArrayList<OnlineCourseDTO>();
		
		for(OnlineCourse onlineCourse: onlineCourses) {
			OnlineCourseDTO dto = new OnlineCourseDTO();
			getInfo(dto, onlineCourse); 
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<OnlineCourseDTO> getByidTeacher(int idTeacher) {
		List<OnlineCourse> onlineCourses = dao.getByidTeacher(idTeacher);
		List<OnlineCourseDTO> dtos = new ArrayList<OnlineCourseDTO>();
		
		for(OnlineCourse onlineCourse: onlineCourses) {
			OnlineCourseDTO dto = new OnlineCourseDTO();
			getInfo(dto, onlineCourse); 
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<OnlineCourseDTO> getByidDiscount(int idDiscount) {
		List<OnlineCourse> onlineCourses = dao.getByidDiscount(idDiscount);
		List<OnlineCourseDTO> dtos = new ArrayList<OnlineCourseDTO>();
		
		for(OnlineCourse onlineCourse: onlineCourses) {
			OnlineCourseDTO dto = new OnlineCourseDTO();
			getInfo(dto, onlineCourse); 
			dtos.add(dto);
		}
		return dtos;
	}

}
