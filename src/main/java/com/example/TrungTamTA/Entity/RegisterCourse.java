package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="register_course")
@Data
public class RegisterCourse implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_course")
	private int idCourse;

	@Column(name="id_combo")
	private int idCombo;

	
	@Column(name="id_student")
	private int idStudent;

	@Column(name="id_class_opening")
	private int idClassOpening;
	
	@Lob
	private String note;
	
	@Column(name = "enable")
	private String enable;
	
	@Column(name="status")
	private int status;

	@Column(name="register_date")
	private Timestamp registerDate;

	@Column(name="updated_date")
	private Timestamp updatedDate;
	
	@Column(name = "soft_delete")
	private int softDelete;
}