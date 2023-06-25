package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.CourseTypeDao;
import com.example.TrungTamTA.Entity.CourseType;
import com.example.TrungTamTA.Model.CourseTypeDTO;
import com.example.TrungTamTA.Service.CourseTypeService;

@Service
@Transactional
public class CourseTypeServiceImpl implements CourseTypeService{

	@Autowired
	CourseTypeDao courseTypeDao;
	
	private void getInfo(CourseTypeDTO courseTypeDTO, CourseType courseType) {
		courseTypeDTO.setId(courseType.getId());
		courseTypeDTO.setName(courseType.getName());
		courseTypeDTO.setDescription(courseType.getDescription());
		courseTypeDTO.setCreatedDate(courseType.getCreatedDate());
		courseTypeDTO.setUpdatedDate(courseType.getUpdatedDate());
	}
	
	private void setInfo(CourseTypeDTO courseTypeDTO, CourseType courseType) {
		courseType.setName(courseTypeDTO.getName());
		courseType.setDescription(courseTypeDTO.getDescription());
	}
	
	@Override
	public List<CourseTypeDTO> getAll() {
		List<CourseType> courseTypes = courseTypeDao.getAll();
		List<CourseTypeDTO> courseTypeDTOs = new ArrayList<CourseTypeDTO>();
		
		for(CourseType courseType: courseTypes) {
			CourseTypeDTO courseTypeDTO = new CourseTypeDTO();
			getInfo(courseTypeDTO, courseType);
			courseTypeDTOs.add(courseTypeDTO);
		}
		return courseTypeDTOs;
	}

	@Override
	public void add(CourseTypeDTO courseTypeDTO) {
		CourseType courseType = new CourseType();
		setInfo(courseTypeDTO, courseType);
		courseType.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		courseType.setUpdatedDate(null);
		courseTypeDao.add(courseType);
	}

	@Override
	public void update(CourseTypeDTO courseTypeDTO) {
		CourseType courseType = courseTypeDao.getByID(courseTypeDTO.getId());
		if(courseType != null) {
			setInfo(courseTypeDTO, courseType);
			courseType.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			courseTypeDao.update(courseType);
		}
	}

	@Override
	public void delete(int id) {
		CourseType courseType = courseTypeDao.getByID(id);
		if(courseType != null) {
			courseTypeDao.delete(courseType);
		}
	}

	@Override
	public CourseTypeDTO getByID(int id) {
		CourseType courseType = courseTypeDao.getByID(id);
		if(courseType != null) {
			CourseTypeDTO courseTypeDTO = new CourseTypeDTO();
			getInfo(courseTypeDTO, courseType);
			return courseTypeDTO;
		}
		return null;
	}

}
