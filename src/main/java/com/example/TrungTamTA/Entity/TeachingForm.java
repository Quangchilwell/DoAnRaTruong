package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name="teaching_form")
@Data
public class TeachingForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String description;

	private String name;

	@Column(name="updated_date")
	private Timestamp updatedDate;

}