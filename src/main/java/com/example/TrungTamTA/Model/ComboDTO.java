package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ComboDTO {
	private int id;
	private float discount;
	private int quantiityCourses;
	private float totalPrice;
	private float newTotalPrice;
	
	private String name;
	private String description;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
