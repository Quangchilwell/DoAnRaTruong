package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class StudentDetailDTO {
	private int id;

	private int idClass;
	private ClassOpeningDTO classOpeningDTO;

	private int idStudent;
	private StudentDTO studentDTO;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
