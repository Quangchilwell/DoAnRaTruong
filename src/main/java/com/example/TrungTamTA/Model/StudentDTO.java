package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StudentDTO {
	private int id;

	private String address;

	private String birthday;

	@NotEmpty
	private String career;

	private Timestamp createdDate;

	private String gender;

	@NotNull
	@NotEmpty
	private String name;

	private String note;

	@NotNull
	@NotEmpty
	private String phone;

	private Timestamp updatedDate;
	private String workPlace;
}
