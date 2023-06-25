package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the class database table.
 * 
 */
@Entity
@Data
@Table(name = "class_room")
public class ClassRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="id_class_type")
	private int idClassType;

	@Lob
	private String name;

	private int status;

	@Column(name="updated_date")
	private Timestamp updatedDate;

}