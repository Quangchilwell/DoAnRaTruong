package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.TeacherDao;
import com.example.TrungTamTA.Entity.Teacher;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	TeacherDao teacherDao;
	
	private void getInfo(TeacherDTO teacherDTO, Teacher teacher)
	{
		teacherDTO.setId(teacher.getId());
		teacherDTO.setName(teacher.getName());
		teacherDTO.setGender(teacher.getGender());
		teacherDTO.setPhone(teacher.getPhone());
		teacherDTO.setEmail(teacher.getEmail());
		teacherDTO.setBirthday(String.valueOf(teacher.getBirthday()));
		teacherDTO.setDegree(teacher.getDegree());
		teacherDTO.setCountry(teacher.getCountry());
		teacherDTO.setAvatar(teacher.getAvatar());
		teacherDTO.setDateOfJoining(String.valueOf(teacher.getDateOfJoining()));
		teacherDTO.setDescription(teacher.getDescription());
		
		teacherDTO.setCreatedDate(teacher.getCreatedDate());
		teacherDTO.setUpdatedDate(teacher.getUpdatedDate());
	}

	private void setInfo(TeacherDTO teacherDTO, Teacher teacher)
	{
		teacher.setName(teacherDTO.getName());
		teacher.setGender(teacherDTO.getGender());
		teacher.setPhone(teacherDTO.getPhone());
		teacher.setEmail(teacherDTO.getEmail());
		teacher.setDegree(teacherDTO.getDegree());
		teacher.setCountry(teacherDTO.getCountry());
		teacher.setBirthday(Date.valueOf(teacherDTO.getBirthday()));
		teacher.setDateOfJoining(Date.valueOf(teacherDTO.getDateOfJoining()));
		teacher.setAvatar(teacherDTO.getAvatar());
		teacher.setDescription(teacherDTO.getDescription());
	}
	@Override
	public List<TeacherDTO> getAll() {
		List<TeacherDTO> teacherDTOs = new ArrayList<TeacherDTO>();
		List<Teacher> teachers = teacherDao.getAll();
		
		for(Teacher teacher: teachers)
		{
			TeacherDTO teacherDTO = new TeacherDTO();
			getInfo(teacherDTO, teacher);
			teacherDTOs.add(teacherDTO); 
		}
		
		return teacherDTOs;
	}

	@Override
	public void add(TeacherDTO teacherDTO) {
		LocalDateTime createdDate = LocalDateTime.now();
		Teacher teacher = new Teacher();
		setInfo(teacherDTO, teacher);
		teacher.setCreatedDate(Timestamp.valueOf(createdDate));
		teacher.setUpdatedDate(null);
		teacherDao.add(teacher);
	}

	@Override
	public void update(TeacherDTO teacherDTO) {
		LocalDateTime updatedDate = LocalDateTime.now();
		Teacher teacher = teacherDao.getByID(teacherDTO.getId());
		if(teacher != null) {
			setInfo(teacherDTO, teacher);
			teacher.setUpdatedDate(Timestamp.valueOf(updatedDate));
			teacherDao.update(teacher);
		}
	}

	@Override
	public void delete(int id) {
		Teacher teacher = teacherDao.getByID(id);
		if(teacher != null) {
			teacherDao.delete(teacher);
		}
	}

	@Override
	public TeacherDTO getByID(int id) {
		Teacher teacher = teacherDao.getByID(id);
		if(teacher != null) {
			TeacherDTO teacherDTO = new TeacherDTO();
			getInfo(teacherDTO, teacher);
			return teacherDTO;
		}
		
		return null;
	}

	@Override
	public List<TeacherDTO> getTeacherForOnlineCourse(int idCourse) {
		List<TeacherDTO> teacherDTOs = new ArrayList<TeacherDTO>();
		List<Teacher> teachers = teacherDao.getTeacherForOnlineCourse(idCourse);
		
		for(Teacher teacher: teachers)
		{
			TeacherDTO teacherDTO = new TeacherDTO();
			getInfo(teacherDTO, teacher);
			teacherDTOs.add(teacherDTO); 
		}
		
		return teacherDTOs;
	}

}
