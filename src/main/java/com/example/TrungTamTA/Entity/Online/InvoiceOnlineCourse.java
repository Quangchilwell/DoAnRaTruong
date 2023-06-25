package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="invoice_online_course")
@Data
public class InvoiceOnlineCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="buy_date")
	private Timestamp buyDate;

	@Column(name="id_account")
	private int idAccount;

	@Column(name="id_online_course")
	private int idOnlineCourse;

	@Lob
	private String note;

	@Column(name="total_price")
	private float totalPrice;

}