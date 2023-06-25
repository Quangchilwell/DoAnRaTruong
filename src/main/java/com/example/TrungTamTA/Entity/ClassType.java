package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


/**
 * The persistent class for the class_type database table.
 * 
 */
@Entity
@Table(name="class_type")
@Data
public class ClassType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	private String name;

	@Lob
	private String note;

	@Column(name="quantity_chairs")
	private int quantityChairs;

	@Column(name="quantity_tables")
	private int quantityTables;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	
}