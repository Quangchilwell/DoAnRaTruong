package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="online_course_activated")
@Data
public class OnlineCourseActivated implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="activation_date")
	private Timestamp activationDate;

	@Column(name="id_account")
	private int idAccount;

	@Column(name="id_online_course")
	private int idOnlineCourse;
}