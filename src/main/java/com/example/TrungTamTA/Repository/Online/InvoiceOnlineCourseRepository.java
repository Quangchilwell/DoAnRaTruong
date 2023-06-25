package com.example.TrungTamTA.Repository.Online;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Online.InvoiceOnlineCourse;

public interface InvoiceOnlineCourseRepository extends JpaRepository<InvoiceOnlineCourse, Integer>{
	public InvoiceOnlineCourse findByid(int id);
	
	public List<InvoiceOnlineCourse> findByidAccount(int idAccount);
	
	public List<InvoiceOnlineCourse> findByidOnlineCourse(int idOnlineCourse);
}
