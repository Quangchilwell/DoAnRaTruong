package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="lesson")
@Data
public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="completed_at")
	private Timestamp completedAt;

	@Temporal(TemporalType.DATE)
	private Date day;

	@Column(name="id_class_opening")
	private int idClassOpening;

	@Column(name = "lesson_number")
	private int lessonNumber;

	private int status;
	
	@Column(name = "reason_postpone")
	private String reasonPostPone;
}