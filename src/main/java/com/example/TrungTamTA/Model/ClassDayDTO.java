package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClassDayDTO {
	private int id;
	private int idClassOpening;
	private ClassOpeningDTO classOpeningDTO;
	
	private int lesson;
	private int status;
	private Timestamp completedAt;
	private String day;
}
