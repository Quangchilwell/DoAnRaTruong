package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="teacher_history")
public class TeacherHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="completion_date")
	private Date completionDate;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_class")
	private int idClass;

	@Column(name="id_teacher")
	private int idTeacher;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}