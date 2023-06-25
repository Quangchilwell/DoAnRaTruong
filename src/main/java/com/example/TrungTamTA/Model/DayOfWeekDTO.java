package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DayOfWeekDTO {
	private int id;
	private int day;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
