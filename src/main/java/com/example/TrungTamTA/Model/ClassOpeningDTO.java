package com.example.TrungTamTA.Model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class ClassOpeningDTO {
	private int id;
	private String name;

	private int idClassRoom;
	private ClassRoomDTO classRoomDTO;
	
	private int idShift;
	private ShiftDTO shiftDTO;
	
	private int idCourse;
	private CourseDTO courseDTO;

	private int idTeacher;
	private TeacherDTO teacherDTO;

	private int idTutor;
	private TutorDTO tutorDTO;

	private String note;
	private int numberOfLessonsLearned;
	private int quantityStudents;
	private int status;

	private Timestamp openingDate;
	private Timestamp updatedDate;
	
	private int dayOfWeeks;
}
