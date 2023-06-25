package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.InvoiceOnlineCourse;
import com.example.TrungTamTA.Model.Online.InvoiceOnlineCourseDTO;

public interface InvoiceOnlineCourseService {
	public List<InvoiceOnlineCourseDTO> getAll();
	
	public List<InvoiceOnlineCourseDTO> getByidAccount(int idAccount);
	
	public List<InvoiceOnlineCourseDTO> getByidOnlineCourse(int idOnlineCourse);
	
	public void add(InvoiceOnlineCourseDTO dto);
	
	public void update(InvoiceOnlineCourseDTO dto);
	
	public void delete(int id);
	
	public InvoiceOnlineCourseDTO getByID(int id);
}
