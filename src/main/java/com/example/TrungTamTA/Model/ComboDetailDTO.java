package com.example.TrungTamTA.Model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ComboDetailDTO {
	private int id;
	
	private int idCombo;
	private ComboDTO comboDTO;

	private int idCourse;
	private CourseDTO courseDTO;

	private float newPrice;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
