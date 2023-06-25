package com.example.TrungTamTA.Model.Online;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class InvoiceOnlineCourseDTO {
	private int id;

	private AccountStudentDTO accountStudentDTO;
	private int idAccount;

	private OnlineCourseDTO onlineCourseDTO;
	private int idOnlineCourse;

	private String note;

	private float totalPrice;
	private Timestamp buyDate;
}
