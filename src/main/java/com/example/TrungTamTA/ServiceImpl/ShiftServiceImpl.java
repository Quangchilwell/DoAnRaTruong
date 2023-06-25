package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ShiftDao;
import com.example.TrungTamTA.Entity.Shift;
import com.example.TrungTamTA.Model.ShiftDTO;
import com.example.TrungTamTA.Service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService{
	
	@Autowired ShiftDao shiftDao;

	private void getInfo(ShiftDTO shiftDTO, Shift shift) {
		shiftDTO.setId(shift.getId());
		shiftDTO.setName(shift.getName());
		shiftDTO.setStartedAt(shift.getStartedAt());
		shiftDTO.setEndedAt(shift.getEndedAt());
		shiftDTO.setCreatedDate(shift.getCreatedDate());
		shiftDTO.setUpdatedDate(shift.getUpdatedDate());
	}
	
	private void setInfo(ShiftDTO shiftDTO, Shift shift) {
		shift.setName(shiftDTO.getName());
		shift.setStartedAt(shiftDTO.getStartedAt());
		shift.setEndedAt(shiftDTO.getEndedAt());
	}
	
	@Override
	public List<ShiftDTO> getAll() {
		List<Shift> shifts = shiftDao.getAll();
		List<ShiftDTO> shiftDTOs = new ArrayList<ShiftDTO>();
		
		for(Shift shift: shifts) {
			ShiftDTO shiftDTO = new ShiftDTO();
			getInfo(shiftDTO, shift);
			shiftDTOs.add(shiftDTO);
		}
		return shiftDTOs;
	}

	@Override
	public void add(ShiftDTO shiftDTO) {
		Shift shift = new Shift();
		setInfo(shiftDTO, shift);
		shiftDao.add(shift);
	}

	@Override
	public void update(ShiftDTO shiftDTO) {
		Shift shift = shiftDao.getByID(shiftDTO.getId());
		if(shift != null) {
			setInfo(shiftDTO, shift);
			shift.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			shiftDao.update(shift);
		}
	}

	@Override
	public void delete(int id) {
		Shift shift = shiftDao.getByID(id);
		if(shift != null) {
			shiftDao.delete(shift);
		}
	}

	@Override
	public ShiftDTO getByID(int id) {
		Shift shift = shiftDao.getByID(id);
		if(shift != null) {
			ShiftDTO shiftDTO = new ShiftDTO();
			getInfo(shiftDTO, shift);
			return shiftDTO;
		}
		return null;
	}
	
	
}
