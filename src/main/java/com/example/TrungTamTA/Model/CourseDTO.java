package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CourseDTO {
	@Id
	private int id;


	private int idCourseType;
	private CourseTypeDTO courseTypeDTO;

	private int idTeachingForm;
	private TeachingFormDTO teachingFormDTO;

	private String level;
	private String name;
	private float studyTime;
	private float tuition;
	private int quantityStudents;
	private int quantityVideos;
	private String endDate;
	private String description;
	private int rank;
	
	private MultipartFile file;
	private String image;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
