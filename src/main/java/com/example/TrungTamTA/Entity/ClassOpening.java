package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="class_opening")
@Data
public class ClassOpening implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_class_room")
	private int idClassRoom;
	
	@Column(name = "id_shift")
	private int idShift;

	@Column(name="id_course")
	private int idCourse;

	@Column(name="id_teacher")
	private int idTeacher;

	@Column(name="id_tutor")
	private int idTutor;

	@Lob
	private String name;

	@Lob
	private String note;

	@Column(name="number_of_lessons_learned")
	private int numberOfLessonsLearned;

	@Column(name="opening_date")
	private Timestamp openingDate;

	@Column(name="quantity_students")
	private int quantityStudents;

	private int status;

	@Column(name="updated_date")
	private Timestamp updatedDate;

}