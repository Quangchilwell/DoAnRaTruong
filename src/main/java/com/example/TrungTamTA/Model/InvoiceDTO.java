package com.example.TrungTamTA.Model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class InvoiceDTO {
	private int id;
	private int idStudent;
	private StudentDTO studentDTO;
	private float moneyPaid;
	private String note;
	private int status;
	private float totalPrice;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
