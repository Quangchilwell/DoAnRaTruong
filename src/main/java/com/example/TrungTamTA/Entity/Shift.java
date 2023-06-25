package com.example.TrungTamTA.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the shift database table.
 * 
 */
@Entity
@Data
public class Shift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	@Column(name="ended_at")
	private String endedAt;

	@Lob
	private String name;

	@Lob
	@Column(name="started_at")
	private String startedAt;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="updated_date")
	private Timestamp updatedDate;
}