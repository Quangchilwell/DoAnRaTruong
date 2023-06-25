package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.TeachingFormDao;
import com.example.TrungTamTA.Entity.TeachingForm;
import com.example.TrungTamTA.Model.TeachingFormDTO;
import com.example.TrungTamTA.Service.TeachingFormService;

@Service
@Transactional
public class TeachingFormServiceImpl implements TeachingFormService{

	@Autowired 
	TeachingFormDao teachingFormDao;
	
	private void getInfo(TeachingFormDTO teachingFormDTO, TeachingForm teachingForm) {
		teachingFormDTO.setId(teachingForm.getId());
		teachingFormDTO.setName(teachingForm.getName());
		teachingFormDTO.setDescription(teachingForm.getDescription());
		teachingFormDTO.setCreatedDate(teachingForm.getCreatedDate());
		teachingFormDTO.setUpdatedDate(teachingForm.getUpdatedDate());
	}
	
	private void setInfo(TeachingFormDTO teachingFormDTO, TeachingForm teachingForm) {
		teachingForm.setName(teachingFormDTO.getName());
		teachingForm.setDescription(teachingForm.getDescription());
	}
	
	@Override
	public List<TeachingFormDTO> getAll() {
		List<TeachingForm> teachingForms = teachingFormDao.getAll();
		List<TeachingFormDTO> teachingFormDTOs = new ArrayList<TeachingFormDTO>();
		
		for(TeachingForm teachingForm: teachingForms) {
			TeachingFormDTO teachingFormDTO = new TeachingFormDTO();
			getInfo(teachingFormDTO, teachingForm);
			teachingFormDTOs.add(teachingFormDTO);
		}
		return teachingFormDTOs;
	}

	@Override
	public void add(TeachingFormDTO teachingFormDTO) {
		TeachingForm teachingForm = new TeachingForm();
		setInfo(teachingFormDTO, teachingForm);
		teachingForm.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		teachingForm.setUpdatedDate(null);
		teachingFormDao.add(teachingForm);
	}

	@Override
	public void update(TeachingFormDTO teachingFormDTO) {
		TeachingForm teachingForm = teachingFormDao.getByID(teachingFormDTO.getId());
		if(teachingForm != null) {
			teachingForm.setName(teachingFormDTO.getName());
			teachingForm.setDescription(teachingFormDTO.getDescription());
			teachingForm.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			teachingFormDao.update(teachingForm);
		}
	}

	@Override
	public void delete(int id) {
		TeachingForm teachingForm = teachingFormDao.getByID(id);
		if(teachingForm != null) {
			teachingFormDao.delete(teachingForm);
		}
	}

	@Override
	public TeachingFormDTO getByID(int id) {
		TeachingForm teachingForm = teachingFormDao.getByID(id);
		if(teachingForm != null) {
			TeachingFormDTO teachingFormDTO = new TeachingFormDTO();
			getInfo(teachingFormDTO, teachingForm);
			return teachingFormDTO;
		}
		return null;
	}

}
