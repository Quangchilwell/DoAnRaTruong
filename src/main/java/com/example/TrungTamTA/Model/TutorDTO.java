package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TutorDTO {
	private int id;
	private String birthDay;
	private String degree;
	private String description;
	private String email;
	private String gender;
	private String joiningDate;
	private String name;
	private String phone;
	private Timestamp createdDate;
	private Timestamp updateDate;
}
