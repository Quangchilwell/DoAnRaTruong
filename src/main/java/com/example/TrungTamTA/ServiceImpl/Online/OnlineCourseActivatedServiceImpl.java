package com.example.TrungTamTA.ServiceImpl.Online;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.OnlineCourseActivatedDao;
import com.example.TrungTamTA.Entity.Online.OnlineCourseActivated;
import com.example.TrungTamTA.Model.Online.AccountStudentDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseActivatedDTO;
import com.example.TrungTamTA.Model.Online.OnlineCourseDTO;
import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.OnlineCourseActivatedService;
import com.example.TrungTamTA.Service.Online.OnlineCourseService;

@Service
public class OnlineCourseActivatedServiceImpl implements OnlineCourseActivatedService{

	@Autowired OnlineCourseActivatedDao dao;
	@Autowired OnlineCourseService onService;
	@Autowired AccountStudentService accountService;
	
	private void getInfo(OnlineCourseActivatedDTO dto, OnlineCourseActivated activated) {
		AccountStudentDTO accountStudentDTO = accountService.getById(activated.getIdAccount());
		OnlineCourseDTO onCourseDTO = onService.getByID(activated.getIdOnlineCourse());
		
		dto.setId(activated.getId());
		dto.setAccountDTO(accountStudentDTO);
		dto.setOnlineCourseDTO(onCourseDTO);
		dto.setActivationDate(activated.getActivationDate());
	}
	
	private void setInfo(OnlineCourseActivatedDTO dto, OnlineCourseActivated activated) {
		activated.setIdAccount(dto.getAccountDTO().getId());
		activated.setIdOnlineCourse(dto.getOnlineCourseDTO().getId());
	}
	
	@Override
	public List<OnlineCourseActivatedDTO> getAll() {
		List<OnlineCourseActivated> activateds = dao.getAll();
		List<OnlineCourseActivatedDTO> dtos = new ArrayList<OnlineCourseActivatedDTO>();
		
		for(OnlineCourseActivated activated: activateds) {
			OnlineCourseActivatedDTO dto = new OnlineCourseActivatedDTO();
			getInfo(dto, activated);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<OnlineCourseActivatedDTO> getByidAccount(int idAccount) {
		List<OnlineCourseActivated> activateds = dao.getByidAccount(idAccount);
		List<OnlineCourseActivatedDTO> dtos = new ArrayList<OnlineCourseActivatedDTO>();
		
		for(OnlineCourseActivated activated: activateds) {
			OnlineCourseActivatedDTO dto = new OnlineCourseActivatedDTO();
			getInfo(dto, activated);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<OnlineCourseActivatedDTO> getByidOnlineCourse(int idOnlineCourse) {
		List<OnlineCourseActivated> activateds = dao.getByidOnlineCourse(idOnlineCourse);
		List<OnlineCourseActivatedDTO> dtos = new ArrayList<OnlineCourseActivatedDTO>();
		
		for(OnlineCourseActivated activated: activateds) {
			OnlineCourseActivatedDTO dto = new OnlineCourseActivatedDTO();
			getInfo(dto, activated);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(OnlineCourseActivatedDTO activatedDTO) {
		OnlineCourseActivated activated = new OnlineCourseActivated();
		setInfo(activatedDTO, activated);
		dao.add(activated);
	}

	@Override
	public void update(OnlineCourseActivatedDTO activatedDTO) {
		OnlineCourseActivated activated = dao.getByID(activatedDTO.getId());
		if(activated != null) {
			setInfo(activatedDTO, activated);
			dao.update(activated);
		}
	}

	@Override
	public void delete(int id) {
		OnlineCourseActivated activated = dao.getByID(id);
		if(activated != null) {
			dao.delete(activated);
		}
	}

	@Override
	public OnlineCourseActivatedDTO getByID(int id) {
		OnlineCourseActivated activated = dao.getByID(id);
		if(activated != null) {
			OnlineCourseActivatedDTO dto = new OnlineCourseActivatedDTO();
			getInfo(dto, activated);
			return dto;
		}
		return null;
	}

}
