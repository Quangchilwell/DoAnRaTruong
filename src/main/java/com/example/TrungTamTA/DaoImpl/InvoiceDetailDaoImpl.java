package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.InvoiceDetailDao;
import com.example.TrungTamTA.Entity.InvoiceDetail;
import com.example.TrungTamTA.Repository.InvoiceDetailRepository;

@Repository
@Transactional
public class InvoiceDetailDaoImpl implements InvoiceDetailDao{

	@Autowired InvoiceDetailRepository repository;
	
	@Override
	public List<InvoiceDetail> getAll() {
		return repository.findAll();
	}

	@Override
	public List<InvoiceDetail> getByidInvoie(int idInvoie) {
		return repository.findByidInvoice(idInvoie);
	}

	@Override
	public void add(InvoiceDetail invoiceDetail) {
		repository.save(invoiceDetail);
	}

	@Override
	public void update(InvoiceDetail invoiceDetail) {
		repository.save(invoiceDetail);
	}

	@Override
	public void delete(InvoiceDetail invoiceDetail) {
		repository.delete(invoiceDetail);
	}

	@Override
	public InvoiceDetail getByID(int id) {
		return repository.findByid(id);
	}

}
