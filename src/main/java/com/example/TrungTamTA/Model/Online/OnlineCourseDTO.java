package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

import com.example.TrungTamTA.Model.CourseDTO;
import com.example.TrungTamTA.Model.CourseTypeDTO;
import com.example.TrungTamTA.Model.TeacherDTO;

import lombok.Data;

@Data
public class OnlineCourseDTO {
	private int id;
	
	private int idCourse;
	private CourseDTO courseDTO;

	private int idDiscount;
	private DiscountCodeDTO discountCodeDTO;

	private int idTeacher;
	private TeacherDTO teacherDTO;
	
	private float reducedPrice;
	private int totalRegisters;
	private int totalVideos;
	private String description;

	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private MultipartFile file;
	private String image;
}
