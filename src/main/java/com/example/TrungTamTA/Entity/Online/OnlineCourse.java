package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="online_course")
@Data
public class OnlineCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Lob
	private String description;

	@Column(name="id_course")
	private int idCourse;

	@Column(name="id_discount")
	private int idDiscount;

	@Column(name="id_teacher")
	private int idTeacher;

	@Lob
	private String image;

	@Column(name="reduced_price")
	private float reducedPrice;

	@Column(name="total_registers")
	private int totalRegisters;

	@Column(name="total_videos")
	private int totalVideos;

	@Column(name="updated_at")
	private Timestamp updatedAt;

}