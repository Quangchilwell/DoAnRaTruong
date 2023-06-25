package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ShiftDTO {
	private int id;
	private String endedAt;
	private String name;
	private String startedAt;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
