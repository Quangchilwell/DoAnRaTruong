package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DiscountCodeDTO {
	private int id;
	private float discountPrice;
	private float discountPercent;
	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
