package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	public Invoice findByid(int id);
	
	public List<Invoice> findByidStudent(int idStudent);
}
