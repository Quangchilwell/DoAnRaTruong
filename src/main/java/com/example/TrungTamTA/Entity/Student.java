package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Lob
	private String career;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String gender;

	@Lob
	private String name;

	@Lob
	private String note;

	@Lob
	private String phone;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@Lob
	@Column(name="work_place")
	private String workPlace;

}