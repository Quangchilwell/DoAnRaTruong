package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DetailAddMoneyDTO {
	private int id;

	private Timestamp createdAt;

	private int idAccount;
	private AccountStudentDTO accountStudentDTO;

	private float money;
}
