package com.example.TrungTamTA.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReservationDTO {
	private int id;
	private Timestamp createdAt;
	private String endDate;
	private int idClass;
	private int idStudent;
	private String note;
	private String reason;
	private String reservationDate;
	private int status;
	private Timestamp updatedDate;
	
	private StudentDTO studentDTO;
	private ClassOpeningDTO classOpeningDTO;
}
