package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.RegisterCourseDao;
import com.example.TrungTamTA.Entity.RegisterCourse;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.RegisterCourseDTO;
import com.example.TrungTamTA.Model.StudentDTO;
import com.example.TrungTamTA.Repository.RegisterCourseRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ComboService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.RegisterCourseService;
import com.example.TrungTamTA.Service.StudentService;

@Service
@Transactional
public class RegisterCourseServiceImpl implements RegisterCourseService {

	@Autowired
	RegisterCourseDao registerCourseDao;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	ComboService comboService;

	@Autowired
	ClassOpeningService classOpeningService;

	@Autowired
	RegisterCourseRepository registerCourseRepository;

	private void getInfo(RegisterCourseDTO registerCourseDTO, RegisterCourse registerCourse) {
		CourseDTO courseDTO = courseService.getByID(registerCourse.getIdCourse());
		StudentDTO studentDTO = studentService.getByID(registerCourse.getIdStudent());
		ClassOpeningDTO classOpeningDTO = classOpeningService.getByID(registerCourse.getIdClassOpening());
		registerCourseDTO.setId(registerCourse.getId());
		if (registerCourse.getIdCombo() != 0) {
			ComboDTO comboDTO = comboService.getByID(registerCourse.getIdCombo());
			registerCourseDTO.setComboDTO(comboDTO);
		}

		registerCourseDTO.setCourseDTO(courseDTO);
		registerCourseDTO.setStudentDTO(studentDTO);
		registerCourseDTO.setClassOpeningDTO(classOpeningDTO);
		registerCourseDTO.setNote(registerCourse.getNote());
		registerCourseDTO.setRegisterDate(registerCourse.getRegisterDate());
		registerCourseDTO.setEnable(registerCourse.getEnable());
		registerCourseDTO.setStatus(registerCourse.getStatus());
		registerCourseDTO.setCreatedDate(registerCourse.getCreatedDate());
		registerCourseDTO.setUpdatedDate(registerCourse.getUpdatedDate());
	}

	private void setInfo(RegisterCourseDTO registerCourseDTO, RegisterCourse registerCourse) {
		if (registerCourseDTO.getComboDTO() != null) {
			registerCourse.setIdCombo(registerCourseDTO.getComboDTO().getId());
		}

		if (registerCourseDTO.getClassOpeningDTO() != null) {
			registerCourse.setIdClassOpening(registerCourseDTO.getClassOpeningDTO().getId());
		} else {
			registerCourse.setIdClassOpening(0);
		}

		registerCourse.setIdCourse(registerCourseDTO.getCourseDTO().getId());
		registerCourse.setIdStudent(registerCourseDTO.getStudentDTO().getId());
		registerCourse.setNote(registerCourseDTO.getNote());
		registerCourse.setStatus(registerCourseDTO.getStatus());
		registerCourse.setRegisterDate(Timestamp.valueOf(LocalDateTime.now()));
		registerCourse.setEnable(registerCourseDTO.getEnable());
	}

	@Override
	public List<RegisterCourseDTO> getAll() {
		List<RegisterCourse> registerCourses = registerCourseDao.getAll();
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getByIdCourse(int idCourse) {
		List<RegisterCourse> registerCourses = registerCourseDao.getByIdCourse(idCourse);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getByIdStudent(int idStudent) {
		List<RegisterCourse> registerCourses = registerCourseDao.getByIdStudent(idStudent);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getByIdClassOpening(int idClass) {
		List<RegisterCourse> registerCourses = registerCourseDao.getByIdClassOpening(idClass);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public void add(RegisterCourseDTO registerCourseDTO) {
		RegisterCourse registerCourse = new RegisterCourse();
		setInfo(registerCourseDTO, registerCourse);
		registerCourse.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		registerCourse.setUpdatedDate(null);
		registerCourseDao.add(registerCourse);
	}

	@Override
	public void addMany(List<RegisterCourseDTO> registerCourseDTOs) {
		List<RegisterCourse> registerCourses = new ArrayList<RegisterCourse>();

		for (RegisterCourseDTO registerCourseDTO : registerCourseDTOs) {
			RegisterCourse registerCourse = new RegisterCourse();
			setInfo(registerCourseDTO, registerCourse);
			registerCourse.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
			registerCourse.setUpdatedDate(null);
			registerCourses.add(registerCourse);
		}

		registerCourseDao.addMany(registerCourses);
	}

	@Override
	public void update(RegisterCourseDTO registerCourseDTO) {
		RegisterCourse registerCourse = registerCourseDao.getByID(registerCourseDTO.getId());
		if (registerCourse != null) {
			setInfo(registerCourseDTO, registerCourse);
			registerCourse.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			registerCourseDao.update(registerCourse);
		}
	}

	@Override
	public void delete(int id) {
		RegisterCourse registerCourse = registerCourseDao.getByID(id);
		if (registerCourse != null) {
			registerCourseDao.delete(registerCourse);
		}
	}

	@Override
	public RegisterCourseDTO getByID(int id) {
		RegisterCourse registerCourse = registerCourseDao.getByID(id);
		if (registerCourse != null) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			return registerCourseDTO;
		}
		return null;
	}

	@Override
	public List<RegisterCourseDTO> getRegistersHaveNotClass() {
		List<RegisterCourse> registerCourses = registerCourseDao.getRegistersHaveNotClass();
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getStudentsInClass(int idClass) {
		List<RegisterCourse> registerCourses = registerCourseDao.getStudentsInClass(idClass);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getRegistersCanOpenClass(int idCourse) {
		List<RegisterCourse> registerCourses = registerCourseDao.getRegistersCanOpenClass(idCourse);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public void softDeleteRegister(RegisterCourseDTO registerCourseDTO) {
		RegisterCourse reCourse = registerCourseDao.getByID(registerCourseDTO.getId());
		if (reCourse != null) {
			reCourse.setSoftDelete(registerCourseDTO.getSoftDelete());
			registerCourseDao.update(reCourse);
		}
	}

	@Override
	public List<RegisterCourseDTO> getByIdClassOpeningAndSoftDelete(int idClass, int softDelete) {
		List<RegisterCourse> registerCourses = registerCourseDao.getByIdClassOpeningAndSoftDelete(idClass, softDelete);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

	@Override
	public List<RegisterCourseDTO> getStudentsToCheckIn(int idClass) {
		List<RegisterCourse> registerCourses = registerCourseRepository.getStudentsToCheckIn(idClass);
		List<RegisterCourseDTO> registerCourseDTOs = new ArrayList<RegisterCourseDTO>();

		for (RegisterCourse registerCourse : registerCourses) {
			RegisterCourseDTO registerCourseDTO = new RegisterCourseDTO();
			getInfo(registerCourseDTO, registerCourse);
			registerCourseDTOs.add(registerCourseDTO);
		}
		return registerCourseDTOs;
	}

}
