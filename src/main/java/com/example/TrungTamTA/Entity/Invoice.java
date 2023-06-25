package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@Data
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_student")
	private int idStudent;

	@Column(name="money_paid")
	private float moneyPaid;

	@Lob
	private String note;

	private int status;

	@Column(name="total_price")
	private float totalPrice;

	@Column(name="updated_date")
	private Timestamp updatedDate;

}