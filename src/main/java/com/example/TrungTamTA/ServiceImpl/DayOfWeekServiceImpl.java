package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.DayOfWeekDao;
import com.example.TrungTamTA.Entity.DayOfWeek;
import com.example.TrungTamTA.Model.DayOfWeekDTO;
import com.example.TrungTamTA.Service.DayOfWeekService;

@Service
@Transactional
public class DayOfWeekServiceImpl implements DayOfWeekService{

	@Autowired DayOfWeekDao dao;
	
	private void getInfo(DayOfWeek dayOfWeek, DayOfWeekDTO dayOfWeekDTO) {
		dayOfWeekDTO.setId(dayOfWeek.getId());
		dayOfWeekDTO.setDay(dayOfWeek.getDay());
		dayOfWeekDTO.setCreatedDate(dayOfWeek.getCreatedDate());
		dayOfWeekDTO.setUpdatedDate(dayOfWeek.getUpdatedDate());
	}
	
	@Override
	public List<DayOfWeekDTO> getAll() {
		List<DayOfWeekDTO> dayOfWeekDTOs = new ArrayList<DayOfWeekDTO>();
		List<DayOfWeek> dayOfWeeks = dao.getAll();
		
		for(DayOfWeek dayOfWeek: dayOfWeeks) {
			DayOfWeekDTO dto = new DayOfWeekDTO();
			getInfo(dayOfWeek, dto);
			dayOfWeekDTOs.add(dto);
		}
		
		return dayOfWeekDTOs;
	}

	@Override
	public void add(DayOfWeekDTO dayOfWeekDTO) {
		DayOfWeek dayOfWeek = new DayOfWeek();
		dayOfWeek.setDay(dayOfWeekDTO.getDay());
		dao.add(dayOfWeek);
	}

	@Override
	public void update(DayOfWeekDTO dayOfWeekDTO) {
		DayOfWeek dayOfWeek = dao.getById(dayOfWeekDTO.getId());
		if(dayOfWeek != null) {
			dayOfWeek.setDay(dayOfWeekDTO.getDay());
			dayOfWeek.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			dao.update(dayOfWeek);
		}
	}

	@Override
	public void delete(int id) {
		DayOfWeek dayOfWeek = dao.getById(id);
		if(dayOfWeek != null) {
			dao.delete(dayOfWeek);
		}
	}

	@Override
	public DayOfWeekDTO getById(int id) {
		DayOfWeek dayOfWeek = dao.getById(id);
		if(dayOfWeek != null) {
			DayOfWeekDTO dto = new DayOfWeekDTO();
			getInfo(dayOfWeek, dto);
			return dto;
		}
		return null;
	}

}
