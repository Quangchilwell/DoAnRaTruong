package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Reservation implements Serializable {
	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="id_class")
	private int idClass;

	@Column(name="id_student")
	private int idStudent;

	@Lob
	private String note;

	@Lob
	private String reason;

	@Temporal(TemporalType.DATE)
	@Column(name="reservation_date")
	private Date reservationDate;

	private int status;

	@Column(name="updated_date")
	private Timestamp updatedDate;
}