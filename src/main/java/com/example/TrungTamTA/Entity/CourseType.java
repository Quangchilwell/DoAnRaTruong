package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="course_type")
public class CourseType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String description;

	@Lob
	private String name;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}