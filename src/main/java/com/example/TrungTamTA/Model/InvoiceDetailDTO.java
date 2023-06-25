package com.example.TrungTamTA.Model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class InvoiceDetailDTO {
	private int id;

	private int idCombo;
	private ComboDTO comboDTO;

	private int idCourse;
	private CourseDTO courseDTO;

	private int idInvoice;
	private InvoiceDTO invoiceDTO;

	private float unitPrice;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
