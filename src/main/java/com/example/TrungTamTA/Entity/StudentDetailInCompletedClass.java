package com.example.TrungTamTA.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "student_detail_in_completed_class")
public class StudentDetailInCompletedClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_class")
	private int idClass;

	@Column(name="id_student")
	private int idStudent;

	@Temporal(TemporalType.DATE)
	private Date completedDate;
	
	@Column(name = "is_passed")
	private int isPassed;
}
