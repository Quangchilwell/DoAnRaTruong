package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the discount_code database table.
 * 
 */
@Entity
@Table(name="discount_code")
@Data
public class DiscountCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="discount_price")
	private float discountPrice;

	@Column(name="discount_percent")
	private float discountPercent;

	@Lob
	private String name;

	@Column(name="updated_at")
	private Timestamp updatedAt;
}