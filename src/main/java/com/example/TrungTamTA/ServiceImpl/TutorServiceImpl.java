package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.TutorDao;
import com.example.TrungTamTA.Entity.Tutor;
import com.example.TrungTamTA.Model.TutorDTO;
import com.example.TrungTamTA.Service.TutorService;

@Service
@Transactional
public class TutorServiceImpl implements TutorService{

	@Autowired TutorDao tutorDao;
	
	private void getInfo(TutorDTO tutorDTO, Tutor tutor) {
		tutorDTO.setId(tutor.getId());
		tutorDTO.setName(tutor.getName());
		tutorDTO.setPhone(tutor.getPhone());
		tutorDTO.setDegree(tutor.getDegree());
		tutorDTO.setBirthDay(String.valueOf(tutor.getBirthDay()));
		tutorDTO.setEmail(tutor.getEmail());
		tutorDTO.setGender(tutor.getGender());
		tutorDTO.setDescription(tutor.getDescription());
		tutorDTO.setJoiningDate(String.valueOf(tutor.getJoiningDate()));
		tutorDTO.setCreatedDate(tutor.getCreatedDate());
		tutorDTO.setUpdateDate(tutor.getUpdateDate());
	}
	
	private void setInfo(TutorDTO tutorDTO, Tutor tutor) {
		tutor.setName(tutorDTO.getName());
		tutor.setPhone(tutorDTO.getPhone());
		tutor.setDegree(tutorDTO.getDegree());
		tutor.setBirthDay(Date.valueOf(tutorDTO.getBirthDay()));
		tutor.setEmail(tutorDTO.getEmail());
		tutor.setGender(tutorDTO.getGender());
		tutor.setDescription(tutorDTO.getDescription());
		tutor.setJoiningDate(Date.valueOf(tutorDTO.getJoiningDate()));
	}
	
	@Override
	public List<TutorDTO> getAll() {
		List<Tutor> tutors = tutorDao.getAll();
		List<TutorDTO> tutorDTOs = new ArrayList<TutorDTO>();
		
		for(Tutor tutor: tutors) {
			TutorDTO tutorDTO = new TutorDTO();
			getInfo(tutorDTO, tutor);
			tutorDTOs.add(tutorDTO);
		}
		return tutorDTOs;
	}

	@Override
	public void add(TutorDTO tutorDTO) {
		Tutor tutor = new Tutor();
		setInfo(tutorDTO, tutor);
		tutorDao.add(tutor); 
	}

	@Override
	public void update(TutorDTO tutorDTO) {
		Tutor tutor = tutorDao.getByID(tutorDTO.getId());
		if(tutor != null) {
			setInfo(tutorDTO, tutor);
			tutor.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
			tutorDao.update(tutor);
		}
	}

	@Override
	public void delete(int id) {
		Tutor tutor = tutorDao.getByID(id);
		if(tutor != null) {
			tutorDao.delete(tutor);
		}
	}

	@Override
	public TutorDTO getByID(int id) {
		Tutor tutor = tutorDao.getByID(id);
		if(tutor != null) {
			TutorDTO tutorDTO = new TutorDTO();
			getInfo(tutorDTO, tutor);
			return tutorDTO;
		}
		return null;
	}

}
