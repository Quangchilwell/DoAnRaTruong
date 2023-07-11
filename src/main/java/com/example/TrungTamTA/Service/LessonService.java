package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.LessonDTO;

public interface LessonService {
	public List<LessonDTO> getAll();
	
	public List<LessonDTO> getByidClass(int idClass);
	
	public List<LessonDTO> getLessonsWereCompleted(int idClass);

	public List<LessonDTO> getLessonsWerePostPone(int idClass);
	
	public void add(LessonDTO lessonDTO);
	
	public void update(LessonDTO lessonDTO);
	
	public void delete(int id);
	
	public LessonDTO getByID(int id);
}
