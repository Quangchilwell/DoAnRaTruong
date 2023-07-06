package com.example.TrungTamTA.Model;

import lombok.Data;

@Data
public class AttendanceDTO {
	private int id;
	private String attendanceDate;
	
	private int idClass;
	private ClassOpeningDTO classDTO;
	
	private int idStudent;
	private StudentDTO studentDTO;
	
	private int status;
}
