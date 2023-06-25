package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="class_day")
@Data
public class ClassDay implements Serializable {
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

	private int lesson;

	private int status;
}