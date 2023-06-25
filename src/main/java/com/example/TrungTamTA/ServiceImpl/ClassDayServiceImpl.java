package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ClassDayDao;
import com.example.TrungTamTA.Entity.ClassDay;
import com.example.TrungTamTA.Model.ClassDayDTO;
import com.example.TrungTamTA.Service.ClassDayService;
import com.example.TrungTamTA.Service.ClassOpeningService;

@Service
public class ClassDayServiceImpl implements ClassDayService{
	
	@Autowired ClassDayDao dao;
	@Autowired ClassOpeningService classOpeningService;

	private void getInfo(ClassDay classDay, ClassDayDTO classDayDTO) {
		classDayDTO.setId(classDay.getId());
		classDayDTO.setClassOpeningDTO(classOpeningService.getByID(classDay.getIdClassOpening()));
		classDayDTO.setDay(String.valueOf(classDay.getDay()));
		classDayDTO.setLesson(classDay.getLesson());
		classDayDTO.setStatus(classDay.getStatus());
		classDayDTO.setCompletedAt(classDay.getCompletedAt());
	}
	
	private void setInfo(ClassDay classDay, ClassDayDTO classDayDTO) {
		
		classDay.setIdClassOpening(classDayDTO.getClassOpeningDTO().getId());
		classDay.setDay(Date.valueOf(classDayDTO.getDay()));
		classDay.setStatus(classDayDTO.getStatus());
		classDay.setLesson(classDayDTO.getLesson());
		classDay.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
	}
	
	@Override
	public List<ClassDayDTO> getAll() {
		List<ClassDayDTO> classDayDTOs = new ArrayList<ClassDayDTO>();
		List<ClassDay> classDays = dao.getAll();
		
		for(ClassDay classDay: classDays) {
			ClassDayDTO classDayDTO = new ClassDayDTO();
			getInfo(classDay, classDayDTO);
			classDayDTOs.add(classDayDTO);
		}
		return classDayDTOs;
	}

	@Override
	public List<ClassDayDTO> getByidClass(int idClass) {
		List<ClassDayDTO> classDayDTOs = new ArrayList<ClassDayDTO>();
		List<ClassDay> classDays = dao.getByidClass(idClass);
		
		for(ClassDay classDay: classDays) {
			ClassDayDTO classDayDTO = new ClassDayDTO();
			getInfo(classDay, classDayDTO);
			classDayDTOs.add(classDayDTO);
		}
		return classDayDTOs;
	}

	@Override
	public void add(ClassDayDTO classDayDTO) {
		ClassDay classDay = new ClassDay();
		setInfo(classDay, classDayDTO);
		dao.add(classDay);
	}

	@Override
	public void update(ClassDayDTO classDayDTO) {
		ClassDay classDay = dao.getByID(classDayDTO.getId());
		if(classDay != null) {
			setInfo(classDay, classDayDTO);
			dao.update(classDay);
		}
	}

	@Override
	public void delete(int id) {
		ClassDay classDay = dao.getByID(id);
		if(classDay != null) {
			dao.delete(classDay);
		}
	}

	@Override
	public ClassDayDTO getByID(int id) {
		ClassDay classDay = dao.getByID(id);
		if(classDay != null) {
			ClassDayDTO dto = new ClassDayDTO();
			getInfo(classDay, dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<ClassDayDTO> getClassDaysWereCompleted(int idClass) {
		List<ClassDayDTO> classDayDTOs = new ArrayList<ClassDayDTO>();
		List<ClassDay> classDays = dao.getClassDaysWereCompleted(idClass);
		
		for(ClassDay classDay: classDays) {
			ClassDayDTO classDayDTO = new ClassDayDTO();
			getInfo(classDay, classDayDTO);
			classDayDTOs.add(classDayDTO);
		}
		return classDayDTOs;
	}

	@Override
	public List<ClassDayDTO> getClassDaysWerePostPone(int idClass) {
		List<ClassDayDTO> classDayDTOs = new ArrayList<ClassDayDTO>();
		List<ClassDay> classDays = dao.getClassDaysWerePostPone(idClass);
		
		for(ClassDay classDay: classDays) {
			ClassDayDTO classDayDTO = new ClassDayDTO();
			getInfo(classDay, classDayDTO);
			classDayDTOs.add(classDayDTO);
		}
		return classDayDTOs;
	}
	
	
}
