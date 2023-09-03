package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Constant.LessonStatus;
import com.example.TrungTamTA.Entity.Lesson;
import com.example.TrungTamTA.Model.LessonDTO;
import com.example.TrungTamTA.Repository.LessonRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{
	
	@Autowired LessonRepository lessonRepository;
	@Autowired ClassOpeningService classOpeningService;

	private void getInfo(Lesson lesson, LessonDTO dto) {
		dto.setId(lesson.getId());
		dto.setClassOpeningDTO(classOpeningService.getByID(lesson.getIdClassOpening()));
		dto.setDay(String.valueOf(lesson.getDay()));
		dto.setLessonNumber(lesson.getLessonNumber());
		dto.setStatus(lesson.getStatus());
		dto.setCompletedAt(lesson.getCompletedAt());
		if(lesson.getReasonPostPone() != null) {
			dto.setReasonPostPone(lesson.getReasonPostPone());
		}
	}
	
	private void setInfo(Lesson lesson, LessonDTO dto) {
		lesson.setIdClassOpening(dto.getClassOpeningDTO().getId());
		lesson.setDay(Date.valueOf(dto.getDay()));
		lesson.setStatus(dto.getStatus());
		lesson.setLessonNumber(dto.getLessonNumber());
		lesson.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
		if(dto.getReasonPostPone() != null) {
			lesson.setReasonPostPone(dto.getReasonPostPone());
		}
	}
	
	@Override
	public List<LessonDTO> getAll() {
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		List<Lesson> lessons = lessonRepository.findAll();
		
		for(Lesson lesson: lessons) {
			LessonDTO dto = new LessonDTO();
			getInfo(lesson, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<LessonDTO> getByidClass(int idClass) {
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		List<Lesson> lessons = lessonRepository.findByidClassOpening(idClass);
		
		for(Lesson lesson: lessons) {
			LessonDTO dto = new LessonDTO();
			getInfo(lesson, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void add(LessonDTO lessonDTO) {
		Lesson lesson = new Lesson();
		setInfo(lesson, lessonDTO);
		lessonRepository.save(lesson);
	}

	@Override
	public List<LessonDTO> getLessonsWereCompleted(int idClass) {
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		List<Lesson> lessons = lessonRepository.findBystatusAndIdClassOpening(LessonStatus.DA_HOAN_THANH, idClass);
		
		for(Lesson lesson: lessons) {
			LessonDTO dto = new LessonDTO();
			getInfo(lesson, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<LessonDTO> getLessonsWerePostPone(int idClass) {
		List<LessonDTO> dtos = new ArrayList<LessonDTO>();
		List<Lesson> lessons = lessonRepository.findBystatusAndIdClassOpening(LessonStatus.HOAN, idClass);
		
		for(Lesson lesson: lessons) {
			LessonDTO dto = new LessonDTO();
			getInfo(lesson, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void update(LessonDTO lessonDTO) {
		Lesson lesson = lessonRepository.findByid(lessonDTO.getId());
		System.out.println(lesson);
		if(lesson != null) {
			setInfo(lesson, lessonDTO);
			lessonRepository.save(lesson);
		}
	}

	@Override
	public void delete(int id) {
		Lesson lesson = lessonRepository.findByid(id);
		if(lesson != null) {
			lessonRepository.delete(lesson);
		}
	}

	@Override
	public LessonDTO getByID(int id) {
		Lesson lesson = lessonRepository.findByid(id);
		if(lesson != null) {
			LessonDTO lessonDTO = new LessonDTO();
			getInfo(lesson, lessonDTO);
			return lessonDTO;
		}
		
		return null;
	}


}
