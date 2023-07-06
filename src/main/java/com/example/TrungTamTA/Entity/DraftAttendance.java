package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="draft_attendance")
@Data
@NamedQuery(name="DraftAttendance.findAll", query="SELECT d FROM DraftAttendance d")
public class DraftAttendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="id_student")
	private int idStudent;
}