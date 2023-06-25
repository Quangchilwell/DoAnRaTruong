package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ComboDetailDao;
import com.example.TrungTamTA.Entity.ComboDetail;
import com.example.TrungTamTA.Model.ComboDetailDTO;
import com.example.TrungTamTA.Service.ComboDetailService;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;

@Service
@Transactional
public class ComboDetailServiceImpl implements ComboDetailService{

	@Autowired ComboDetailDao comboDetailDao;
	
	@Autowired ComboService comboService;
	
	@Autowired CourseService courseService;
	
	private void getInfo(ComboDetailDTO comboDetailDTO, ComboDetail comboDetail) {
		comboDetailDTO.setId(comboDetail.getId());
		comboDetailDTO.setCourseDTO(courseService.getByID(comboDetail.getIdCourse()));
		comboDetailDTO.setComboDTO(comboService.getByID(comboDetail.getIdCombo()));
		comboDetailDTO.setNewPrice(comboDetail.getNewPrice());
		comboDetailDTO.setCreatedDate(comboDetail.getCreatedDate());
		comboDetailDTO.setUpdatedDate(comboDetail.getUpdatedDate());
	}
	
	private void setInfo(ComboDetailDTO comboDetailDTO, ComboDetail comboDetail) {
		comboDetail.setIdCombo(comboDetailDTO.getComboDTO().getId());
		comboDetail.setIdCourse(comboDetailDTO.getCourseDTO().getId());
		comboDetail.setNewPrice(comboDetailDTO.getNewPrice());
	}
	
	@Override
	public List<ComboDetailDTO> getAll() {
		List<ComboDetailDTO> comboDetailDTOs = new ArrayList<ComboDetailDTO>();
		List<ComboDetail> comboDetails = comboDetailDao.getAll();
		
		for(ComboDetail comboDetail: comboDetails) {
			ComboDetailDTO comboDetailDTO = new ComboDetailDTO();
			getInfo(comboDetailDTO, comboDetail);
			comboDetailDTOs.add(comboDetailDTO);
		}
		return comboDetailDTOs;
	}

	@Override
	public List<ComboDetailDTO> getByidCourse(int idCourse) {
		List<ComboDetailDTO> comboDetailDTOs = new ArrayList<ComboDetailDTO>();
		List<ComboDetail> comboDetails = comboDetailDao.getByidCourse(idCourse);
		
		for(ComboDetail comboDetail: comboDetails) {
			ComboDetailDTO comboDetailDTO = new ComboDetailDTO();
			getInfo(comboDetailDTO, comboDetail);
			comboDetailDTOs.add(comboDetailDTO);
		}
		return comboDetailDTOs;
	}

	@Override
	public List<ComboDetailDTO> getByidCombo(int idCombo) {
		List<ComboDetailDTO> comboDetailDTOs = new ArrayList<ComboDetailDTO>();
		List<ComboDetail> comboDetails = comboDetailDao.getByidCombo(idCombo);
		
		for(ComboDetail comboDetail: comboDetails) {
			ComboDetailDTO comboDetailDTO = new ComboDetailDTO();
			getInfo(comboDetailDTO, comboDetail);
			comboDetailDTOs.add(comboDetailDTO);
		}
		return comboDetailDTOs;
	}

	@Override
	public void add(ComboDetailDTO comboDetailDTO) {
		ComboDetail comboDetail = new ComboDetail();
		setInfo(comboDetailDTO, comboDetail);
		comboDetail.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		comboDetailDao.add(comboDetail);
	}

	@Override
	public void update(ComboDetailDTO comboDetailDTO) {
		ComboDetail comboDetail = comboDetailDao.getByID(comboDetailDTO.getId());
		if(comboDetail != null) {
			setInfo(comboDetailDTO, comboDetail);
			comboDetail.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			comboDetailDao.update(comboDetail);
		}
	}

	@Override
	public void delete(int id) {
		ComboDetail comboDetail = comboDetailDao.getByID(id);
		if(comboDetail != null) {
			comboDetailDao.delete(comboDetail);
		}
	}

	@Override
	public ComboDetailDTO getByID(int id) {
		ComboDetail comboDetail = comboDetailDao.getByID(id);
		if(comboDetail != null) {
			ComboDetailDTO comboDetailDTO = new ComboDetailDTO();
			getInfo(comboDetailDTO, comboDetail);
			return comboDetailDTO;
		}
		return null;
	}

}
