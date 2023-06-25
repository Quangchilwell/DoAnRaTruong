package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OnlineCourseActivatedDTO {
	private int id;
	private Timestamp activationDate;
	
	private AccountStudentDTO accountDTO; 
	private int idAccount;
	
	private OnlineCourseDTO onlineCourseDTO;
	private int idOnlineCourse;
}
