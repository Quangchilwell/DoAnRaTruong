package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RegisterCourseDTO {
	private int id;

	private int idCourse;
	private CourseDTO courseDTO;

	private int idStudent;
	private StudentDTO studentDTO;

	private int idCombo;
	private ComboDTO comboDTO;
	
	private int idClassOpening;
	private ClassOpeningDTO classOpeningDTO;
	
	private String note;
	private int status;
	
	private String enable;
	private int softDelete;
	
	private Timestamp createdDate;
	private Timestamp registerDate;
	private Timestamp updatedDate;
}
