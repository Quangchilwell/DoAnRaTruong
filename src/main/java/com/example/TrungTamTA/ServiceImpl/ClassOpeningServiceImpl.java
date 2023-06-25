package com.example.TrungTamTA.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ClassOpeningDao;
import com.example.TrungTamTA.Entity.ClassOpening;
import com.example.TrungTamTA.Model.ClassOpeningDTO;
import com.example.TrungTamTA.Model.ClassRoomDTO;
import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.ShiftDTO;
import com.example.TrungTamTA.Model.TeacherDTO;
import com.example.TrungTamTA.Model.TutorDTO;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ClassRoomService;
import com.example.TrungTamTA.Service.CourseService;
import com.example.TrungTamTA.Service.ShiftService;
import com.example.TrungTamTA.Service.TeacherService;
import com.example.TrungTamTA.Service.TutorService;

@Service
public class ClassOpeningServiceImpl implements ClassOpeningService {

	@Autowired
	ClassOpeningDao dao;

	@Autowired
	ClassRoomService classRoomService;
	
	@Autowired ShiftService shiftService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	TutorService tutorService;

	@Autowired
	CourseService courseService;

	private void getInfo(ClassOpeningDTO classOpeningDTO, ClassOpening classOpening) {
		TeacherDTO teacherDTO = teacherService.getByID(classOpening.getIdTeacher());
		TutorDTO tutorDTO = tutorService.getByID(classOpening.getIdTutor());
		CourseDTO courseDTO = courseService.getByID(classOpening.getIdCourse());
		ClassRoomDTO classRoomDTO = classRoomService.getByID(classOpening.getIdClassRoom());
		ShiftDTO shiftDTO = shiftService.getByID(classOpening.getIdShift());

		classOpeningDTO.setId(classOpening.getId());
		classOpeningDTO.setName(classOpening.getName());
		classOpeningDTO.setTeacherDTO(teacherDTO);
		classOpeningDTO.setCourseDTO(courseDTO);
		classOpeningDTO.setTutorDTO(tutorDTO);
		classOpeningDTO.setClassRoomDTO(classRoomDTO);
		classOpeningDTO.setShiftDTO(shiftDTO);
		
		classOpeningDTO.setOpeningDate(classOpening.getOpeningDate());
		classOpeningDTO.setQuantityStudents(classOpening.getQuantityStudents());
		classOpeningDTO.setNumberOfLessonsLearned(classOpening.getNumberOfLessonsLearned());
		classOpeningDTO.setNote(classOpening.getNote());
		classOpeningDTO.setStatus(classOpening.getStatus());
		classOpeningDTO.setUpdatedDate(classOpening.getUpdatedDate());

	}

	private void setInfo(ClassOpeningDTO classOpeningDTO, ClassOpening classOpening) {
		classOpening.setName(classOpeningDTO.getName());
		
		if(classOpeningDTO.getTeacherDTO() != null) {
			classOpening.setIdTeacher(classOpeningDTO.getTeacherDTO().getId());			
		}
		
		if(classOpeningDTO.getCourseDTO() != null) {
			classOpening.setIdCourse(classOpeningDTO.getCourseDTO().getId());			
		}
		
		if(classOpeningDTO.getClassRoomDTO() != null){
			classOpening.setIdClassRoom(classOpeningDTO.getClassRoomDTO().getId());			
		}
		
		if(classOpeningDTO.getTutorDTO() != null) {			
			classOpening.setIdTutor(classOpeningDTO.getTutorDTO().getId());
		}
		
		if(classOpeningDTO.getShiftDTO() != null) {
			classOpening.setIdShift(classOpeningDTO.getShiftDTO().getId());	
		}
		
		classOpening.setNumberOfLessonsLearned(classOpeningDTO.getNumberOfLessonsLearned());
		classOpening.setQuantityStudents(classOpeningDTO.getQuantityStudents());
		classOpening.setNote(classOpeningDTO.getNote());
		classOpening.setStatus(classOpeningDTO.getStatus());;
	}

	@Override
	public List<ClassOpeningDTO> getAll() {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getAll();
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getByidTeacher(int idTeacher) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getByidTeacher(idTeacher);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getByidCourse(int idCourse) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getByidCourse(idCourse);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getByidClassRoom(int idRoom) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getByidClassRoom(idRoom);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getByidTutor(int idTutor) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getByidTutor(idTutor);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public void add(ClassOpeningDTO classOpeningDTO) {
		ClassOpening classOpening = new ClassOpening();
		setInfo(classOpeningDTO, classOpening);
		dao.add(classOpening);
	}

	@Override
	public void update(ClassOpeningDTO classOpeningDTO) {
		ClassOpening classOpening = dao.getByID(classOpeningDTO.getId());
		if(classOpening != null) {
			setInfo(classOpeningDTO, classOpening);
			dao.update(classOpening);
		}
	}

	@Override
	public void delete(int id) {
		ClassOpening classOpening = dao.getByID(id);
		if(classOpening != null) {
			dao.delete(classOpening);		}
	}

	@Override
	public ClassOpeningDTO getByID(int id) {
		ClassOpening classOpening = dao.getByID(id);
		if(classOpening != null) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			return dto;
		}
		return null;
	}

	@Override
	public List<ClassOpeningDTO> getDuplicateClassList(int idShift, int idClassOpening) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getDuplicateClassList(idShift, idClassOpening);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getActivateClasses() {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getActivateClasses();
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;
	}

	@Override
	public List<ClassOpeningDTO> getByStatus(int status) {
		List<ClassOpeningDTO> classOpeningDTOs = new ArrayList<ClassOpeningDTO>();
		List<ClassOpening> classOpenings = dao.getBystatus(status);
		
		for(ClassOpening classOpening: classOpenings) {
			ClassOpeningDTO dto = new ClassOpeningDTO();
			getInfo(dto, classOpening);
			classOpeningDTOs.add(dto);
		}
		
		return classOpeningDTOs;	
	}

	@Override
	public void updateStatusOfClass(ClassOpeningDTO classOpeningDTO) {
		ClassOpening classOpening = dao.getByID(classOpeningDTO.getId());
		if(classOpening != null) {
			classOpening.setStatus(classOpeningDTO.getStatus());
			dao.update(classOpening);		
		}
	}
}
