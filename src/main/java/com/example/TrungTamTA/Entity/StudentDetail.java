package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="student_detail")
@Data
public class StudentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_class")
	private int idClass;

	@Column(name="id_student")
	private int idStudent;

	@Column(name="updated_date")
	private Timestamp updatedDate;

}