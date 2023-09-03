package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TeacherHistoryDTO {
	private int id;
	private String completionDate;
	private int idClass;
	private int idTeacher;
	
	private TeacherDTO teacherDTO;
	private ClassOpeningDTO classDTO;
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
