package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="future_student")
@NamedQuery(name="FutureStudent.findAll", query="SELECT f FROM FutureStudent f")
@Data
public class FutureStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String aspiration;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String email;

	@Lob
	private String name;

	@Lob
	private String phone;

	private int status;
	
	private Timestamp schedule;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}