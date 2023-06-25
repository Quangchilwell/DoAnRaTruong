package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer>{
	public InvoiceDetail findByid(int id);
	
	public List<InvoiceDetail> findByidInvoice(int idInvoice);
}
