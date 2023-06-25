package com.example.TrungTamTA.Model;

import lombok.Data;

@Data
public class StudentDetailInCompletedClassDTO {
	private int id;
	
	private int idClass;
	private ClassOpeningDTO classOpeningDTO;

	private int idStudent;
	private StudentDTO studentDTO;

	private String completedDate;
	private int isPassed;
}
