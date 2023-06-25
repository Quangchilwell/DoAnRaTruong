package com.example.TrungTamTA.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.StudentDetailDao;
import com.example.TrungTamTA.Entity.StudentDetail;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Model.StudentDetailDTO;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.StudentDetailService;
import com.example.TrungTamTA.Service.StudentService;

@Service
@Transactional
public class StudentDetailServiceImpl implements StudentDetailService{

	@Autowired StudentDetailDao dao;
	
	@Autowired StudentService studentService;
	
	@Autowired ClassOpeningService classOpeningService;
	
	private void getInfo(StudentDetailDTO dto, StudentDetail studentDetail) {
		StudentDTO studentDTO = studentService.getByID(studentDetail.getIdStudent());
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(studentDetail.getIdClass());
		
		dto.setId(studentDetail.getId());
		dto.setStudentDTO(studentDTO);
		dto.setClassOpeningDTO(classOpeningDTO);
		dto.setCreatedDate(studentDetail.getCreatedDate());
		dto.setUpdatedDate(studentDetail.getUpdatedDate());
	}
	
	private void setInfo(StudentDetailDTO dto, StudentDetail studentDetail) {
		studentDetail.setIdClass(dto.getClassOpeningDTO().getId());
		studentDetail.setIdStudent(dto.getStudentDTO().getId());
	}
	
	@Override
	public List<StudentDetailDTO> getAll() {
		List<StudentDetail> studentDetails = dao.getAll();
		List<StudentDetailDTO> studentDetailDTOs = new ArrayList<StudentDetailDTO>();
		
		for(StudentDetail studentDetail: studentDetails) {
			StudentDetailDTO dto = new StudentDetailDTO();
			getInfo(dto, studentDetail);
			studentDetailDTOs.add(dto);
		}
		
		return studentDetailDTOs;
	}

	@Override
	public List<StudentDetailDTO> getByidStudent(int idStudent) {
		List<StudentDetail> studentDetails = dao.getByidStudent(idStudent);
		List<StudentDetailDTO> studentDetailDTOs = new ArrayList<StudentDetailDTO>();
		
		for(StudentDetail studentDetail: studentDetails) {
			StudentDetailDTO dto = new StudentDetailDTO();
			getInfo(dto, studentDetail);
			studentDetailDTOs.add(dto);
		}
		
		return studentDetailDTOs;
	}

	@Override
	public List<StudentDetailDTO> getByidClass(int idClass) {
		List<StudentDetail> studentDetails = dao.getByidClass(idClass);
		List<StudentDetailDTO> studentDetailDTOs = new ArrayList<StudentDetailDTO>();
		
		for(StudentDetail studentDetail: studentDetails) {
			StudentDetailDTO dto = new StudentDetailDTO();
			getInfo(dto, studentDetail);
			studentDetailDTOs.add(dto);
		}
		
		return studentDetailDTOs;
	}

	@Override
	public void add(StudentDetailDTO studentDetailDTO) {
		StudentDetail studentDetail = new StudentDetail();
		setInfo(studentDetailDTO, studentDetail);
		dao.add(studentDetail);
	}

	@Override
	public void update(StudentDetailDTO studentDetailDTO) {
		StudentDetail studentDetail = dao.getByID(studentDetailDTO.getId());
		if(studentDetail != null) {
			setInfo(studentDetailDTO, studentDetail);
			dao.update(studentDetail);
		}
	}

	@Override
	public void delete(int id) {
		StudentDetail studentDetail = dao.getByID(id);
		if(studentDetail != null) {
			dao.delete(studentDetail);
		}
	}

	@Override
	public StudentDetailDTO getByID(int id) {
		StudentDetail studentDetail = dao.getByID(id);
		if(studentDetail != null) {
			StudentDetailDTO dto = new StudentDetailDTO();
			getInfo(dto, studentDetail);
			return dto;
		}
		return null;
	}
	
}
