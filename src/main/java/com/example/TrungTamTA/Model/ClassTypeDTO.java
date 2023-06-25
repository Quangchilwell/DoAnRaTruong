package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NonNull;

@Data
public class ClassTypeDTO {
	private int id;
	
	@NotNull
	@NotEmpty
    private String name;
	
    private int quantityTables;
    private int quantityChairs;
    private String note;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
