package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.StudentDao;
import com.example.TrungTamTA.Entity.Student;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao studentDao;
	
	private void getInfo(StudentDTO studentDTO, Student student) {
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setBirthday(String.valueOf(student.getBirthday()));
		studentDTO.setPhone(student.getPhone());
		studentDTO.setAddress(student.getAddress());
		studentDTO.setCareer(student.getCareer());
		studentDTO.setGender(student.getGender());
		studentDTO.setWorkPlace(student.getWorkPlace());
		studentDTO.setNote(student.getNote());
		
		studentDTO.setCreatedDate(student.getCreatedDate());
		studentDTO.setUpdatedDate(student.getUpdatedDate());
	}
	
	private void setInfo(StudentDTO studentDTO, Student student) {
		student.setName(studentDTO.getName());
		student.setBirthday(Date.valueOf(studentDTO.getBirthday()));
		student.setPhone(studentDTO.getPhone());
		student.setAddress(studentDTO.getAddress());
		student.setCareer(studentDTO.getCareer());
		student.setGender(studentDTO.getGender());
		student.setWorkPlace(studentDTO.getWorkPlace());
		student.setNote(studentDTO.getNote());
		
	}
	
	@Override
	public List<StudentDTO> getAll() {
		List<Student> students = studentDao.getAll();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		
		for(Student student: students) {
			StudentDTO studentDTO = new StudentDTO();
			getInfo(studentDTO, student);
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
	public void add(StudentDTO studentDTO) {
		Student student = new Student();
		setInfo(studentDTO, student);
		student.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		student.setUpdatedDate(null);
		studentDao.add(student);
	}

	@Override
	public void update(StudentDTO studentDTO) {
		Student student = studentDao.getByID(studentDTO.getId());
		if(student != null) {
			setInfo(studentDTO, student);
			student.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			studentDao.add(student);
		}
	}

	@Override
	public void delete(int id) {
		Student student = studentDao.getByID(id);
		if(student != null) {
			studentDao.delete(student);
		}
	}

	@Override
	public StudentDTO getByID(int id) {
		Student student = studentDao.getByID(id);
		if(student != null) {
			StudentDTO studentDTO = new StudentDTO();
			getInfo(studentDTO, student);
			return studentDTO;
		}
		return null;
	}

}
