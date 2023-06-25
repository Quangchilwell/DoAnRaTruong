package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.InvoiceDTO;

public interface InvoiceService {
	public List<InvoiceDTO> getAll();
	
	public List<InvoiceDTO> getByidStudent(int idStudent);
	
	public void add(InvoiceDTO invoiceDTO);
	
	public void update(InvoiceDTO invoiceDTO);
	
	public void delete(int id);
	
	public InvoiceDTO getByID(int id);
}
