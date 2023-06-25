package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FutureStudentDTO {
	private int id;
	private String email;
	private String name;
	private String phone;
	private String aspiration;
	private int status;
	private Timestamp schedule;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
