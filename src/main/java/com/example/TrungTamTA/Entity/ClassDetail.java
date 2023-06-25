package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="class_detail")
@Data
public class ClassDetail implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_class_opening")
	private int idClassOpening;

	@Column(name="id_day_of_week")
	private int idDayOfWeek;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="learning_date")
	private Date learningDate;
}