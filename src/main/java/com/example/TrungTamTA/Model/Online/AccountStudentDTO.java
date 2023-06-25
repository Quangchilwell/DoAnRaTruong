package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AccountStudentDTO {
	private int id;
	private String email;
	private float moneyAvailable;
	private String name;
	private String note;
	private String password;
	private String phone;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
