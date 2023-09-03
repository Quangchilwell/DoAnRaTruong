package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="student_history")
public class StudentHistory implements Serializable {

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="completion_date")
	private Date completionDate;

	@Column(name="completion_status")
	private int completionStatus;

	@Column(name="id_class")
	private int idClass;

	@Column(name="id_student")
	private int idStudent;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="updated_at")
	private Date updatedAt;

}