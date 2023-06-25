package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;


@Entity
@Data
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="id_course_type")
	private int idCourseType;

	@Column(name="id_teaching_form")
	private int idTeachingForm;

	@Lob
	private String image;

	@Lob
	private String level;

	@Lob
	private String name;

	@Column(name="quantity_students")
	private int quantityStudents;

	@Column(name="quantity_videos")
	private int quantityVideos;

	@Column(name="study_time")
	private float studyTime;

	private float tuition;

	@Column(name="updated_date")
	private Timestamp updatedDate;
	
	@Column(name = "rank")
	private int rank;

}