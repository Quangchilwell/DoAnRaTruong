package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TeachingFormDTO {
	private int id;
	
	@NotNull
	@NotEmpty
	private String name;
	private String description;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
