package com.example.TrungTamTA.Model;

import java.util.Date;

import lombok.Data;

@Data
public class ClassDetailDTO {
	private int id;
	
	private int idClassOpening;
	private ClassOpeningDTO classOpeningDTO;
	
	private int idDayOfWeek;
	private DayOfWeekDTO dayOfWeekDTO;
	
	private String learningDate;
}
