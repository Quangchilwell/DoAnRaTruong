package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.InvoiceDetail;

public interface InvoiceDetailDao {
	public List<InvoiceDetail> getAll();
	
	public List<InvoiceDetail> getByidInvoie(int idInvoie);
	
	public void add(InvoiceDetail invoiceDetail);
	
	public void update(InvoiceDetail invoiceDetail);
	
	public void delete(InvoiceDetail invoiceDetail);
	
	public InvoiceDetail getByID(int id);
	
}
