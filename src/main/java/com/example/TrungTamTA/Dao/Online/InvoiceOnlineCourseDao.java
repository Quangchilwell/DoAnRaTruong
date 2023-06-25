package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.InvoiceOnlineCourse;

public interface InvoiceOnlineCourseDao {
	public List<InvoiceOnlineCourse> getAll();
	
	public List<InvoiceOnlineCourse> getByidAccount(int idAccount);
	
	public List<InvoiceOnlineCourse> getByidOnlineCourse(int idOnlineCourse);
	
	public void add(InvoiceOnlineCourse invoiceOnlineCourse);
	
	public void update(InvoiceOnlineCourse invoiceOnlineCourse);
	
	public void delete(InvoiceOnlineCourse invoiceOnlineCourse);
	
	public InvoiceOnlineCourse getByID(int id);
}
