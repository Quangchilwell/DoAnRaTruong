package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the combo_detail database table.
 * 
 */
@Entity
@Table(name="combo_detail")
@Data
public class ComboDetail implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_combo")
	private int idCombo;

	@Column(name="id_course")
	private int idCourse;

	@Column(name="new_price")
	private float newPrice;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}