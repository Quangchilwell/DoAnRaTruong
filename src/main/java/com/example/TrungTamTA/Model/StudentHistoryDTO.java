package com.example.TrungTamTA.Model;

import lombok.Data;

@Data
public class StudentHistoryDTO {
	private int id;
	private String completionDate;
	private int completionStatus;
	private int idClass;
	private int idStudent;
	
	private StudentDTO studentDTO;
	private ClassOpeningDTO classDTO;
}
