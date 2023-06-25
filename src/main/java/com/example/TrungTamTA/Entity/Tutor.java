package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Tutor implements Serializable {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_day")
	private Date birthDay;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String degree;

	@Lob
	private String description;

	@Lob
	private String email;

	@Lob
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name="joining_date")
	private Date joiningDate;

	@Lob
	private String name;

	@Lob
	private String phone;

	@Column(name="update_date")
	private Timestamp updateDate;

}