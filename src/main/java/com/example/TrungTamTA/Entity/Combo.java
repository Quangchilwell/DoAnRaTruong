package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


@Entity
@Data
public class Combo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String description;

	private float discount;

	@Lob
	private String name;

	@Column(name="quantiity_courses")
	private int quantiityCourses;

	@Column(name="total_price")
	private float totalPrice;

	@Column(name="new_total_price")
	private float newTotalPrice;
	
	@Column(name="updated_date")
	private Timestamp updatedDate;
}