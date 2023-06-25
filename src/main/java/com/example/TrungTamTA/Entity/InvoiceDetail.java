package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


/**
 * The persistent class for the invoice_detail database table.
 * 
 */
@Entity
@Table(name="invoice_detail")
@Data
public class InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_combo")
	private int idCombo;

	@Column(name="id_course")
	private int idCourse;

	@Column(name="id_invoice")
	private int idInvoice;

	@Column(name="unit_price")
	private float unitPrice;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}