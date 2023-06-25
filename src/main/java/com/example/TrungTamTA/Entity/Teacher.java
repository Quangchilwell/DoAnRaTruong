package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "teacher")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Lob
	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_joining")
	private Date dateOfJoining;

	@Lob
	private String degree;

	@Lob
	private String gender;

	@Lob
	private String name;

	@Lob
	private String phone;
	
	@Lob
	private String email;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="updated_date")
	private Timestamp updatedDate;

}