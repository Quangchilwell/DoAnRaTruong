package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TeacherDTO {
	private int id;
	
	@NotNull
	@NotEmpty
	private String degree;
	private String gender;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String phone;
	
	@NotNull
	@NotEmpty
	private String email;
	private String avatar;
	private String country;
	
	@NotNull
	@NotEmpty
	private String birthday;
	
	@NotNull
	@NotEmpty
	private String dateOfJoining;
	private String description;
	
	private MultipartFile file;
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
