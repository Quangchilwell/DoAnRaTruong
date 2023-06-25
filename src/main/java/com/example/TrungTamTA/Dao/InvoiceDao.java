package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Invoice;

public interface InvoiceDao {
	public List<Invoice> getAll();
	
	public List<Invoice> getByidStudent(int idStudent);
	
	public Invoice add(Invoice invoice);
	
	public void update(Invoice invoice);
	
	public void delete(Invoice invoice);
	
	public Invoice getByID(int id);
}
