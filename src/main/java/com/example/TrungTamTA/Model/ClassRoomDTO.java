package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClassRoomDTO {
	private int id;
	private int idType;
	private ClassTypeDTO classTypeDTO;
	
	@NotNull
	@NotEmpty
	private String name;
	
	private int status;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
