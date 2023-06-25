package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.CourseDao;
import com.example.TrungTamTA.Entity.Course;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.CourseTypeDTO;
import com.example.TrungTamTA.Model.TeachingFormDTO;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.CourseTypeService;
import com.example.TrungTamTA.Service.TeachingFormService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseDao courseDao;
	
	@Autowired
	CourseTypeService typeService;
	
	@Autowired
	TeachingFormService formService;
	
	private void getInfo(CourseDTO courseDTO, Course course) {
		CourseTypeDTO courseTypeDTO = typeService.getByID(course.getIdCourseType());
		TeachingFormDTO teachingFormDTO = formService.getByID(course.getIdTeachingForm());
		
		courseDTO.setId(course.getId());
		courseDTO.setName(course.getName());
		courseDTO.setCourseTypeDTO(courseTypeDTO);
		courseDTO.setTeachingFormDTO(teachingFormDTO);
		courseDTO.setLevel(course.getLevel());
		courseDTO.setTuition(course.getTuition());
		courseDTO.setStudyTime(course.getStudyTime());
		courseDTO.setRank(course.getRank());
		courseDTO.setQuantityStudents(course.getQuantityStudents());
		courseDTO.setQuantityVideos(course.getQuantityVideos());
		courseDTO.setEndDate(String.valueOf(course.getEndDate()));
		courseDTO.setImage(course.getImage());
		courseDTO.setDescription(course.getDescription());
		courseDTO.setCreatedDate(course.getCreatedDate());
		courseDTO.setUpdatedDate(course.getUpdatedDate());
	}
	
	private void setInfo(CourseDTO courseDTO, Course course) {
		course.setName(courseDTO.getName());
		course.setIdCourseType(courseDTO.getCourseTypeDTO().getId());
		course.setIdTeachingForm(courseDTO.getTeachingFormDTO().getId());
		course.setLevel(courseDTO.getLevel());
		course.setTuition(courseDTO.getTuition());
		course.setStudyTime(courseDTO.getStudyTime());
		course.setImage(courseDTO.getImage());
		course.setDescription(courseDTO.getDescription());
	}
	
	@Override
	public List<CourseDTO> getAll() {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getAll();
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@Override
	public List<CourseDTO> getByIdType(int idType) {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getByIdType(idType);
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@Override
	public List<CourseDTO> getByIdForm(int idForm) {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getByIdForm(idForm);
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}
	
	@Override
	public List<CourseDTO> getCoursesNotInCombo(int idCombo) {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getCoursesNotInCombo(idCombo);
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@Override
	public void add(CourseDTO courseDTO) {
		Course course = new Course();
		setInfo(courseDTO, course);
		course.setQuantityStudents(0);
		course.setQuantityVideos(0);
		course.setEndDate(null);
		courseDao.add(course);
	}

	@Override
	public void update(CourseDTO courseDTO) {
		Course course = courseDao.getByID(courseDTO.getId());
		if(course != null) {
			setInfo(courseDTO, course);
			course.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			courseDao.update(course);
		}
	}

	@Override
	public void delete(int id) {
		Course course = courseDao.getByID(id);
		if(course != null) {
			courseDao.delete(course);
		}
	}

	@Override
	public CourseDTO getByID(int id) {
		Course course = courseDao.getByID(id);
		if(course != null) {
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			return courseDTO;
		}
		return null;
	}

	@Override
	public List<CourseDTO> getOfflineCourses() {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getOfflineCourses();
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@Override
	public List<CourseDTO> getOnlineCourses() {
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		List<Course> courses = courseDao.getOnlinelineCourses();
		
		for(Course course: courses)
		{
			CourseDTO courseDTO = new CourseDTO();
			getInfo(courseDTO, course);
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

}
