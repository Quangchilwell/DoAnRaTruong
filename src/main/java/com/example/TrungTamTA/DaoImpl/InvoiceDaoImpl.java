package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.InvoiceDao;
import com.example.TrungTamTA.Entity.Invoice;
import com.example.TrungTamTA.Repository.InvoiceRepository;

@Repository
@Transactional
public class InvoiceDaoImpl implements InvoiceDao{

	@Autowired InvoiceRepository repository;
	
	@Override
	public List<Invoice> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Invoice> getByidStudent(int idStudent) {
		// TODO Auto-generated method stub
		return repository.findByidStudent(idStudent);
	}

	@Override
	public Invoice add(Invoice invoice) {
		// TODO Auto-generated method stub
		return repository.save(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		repository.save(invoice);
	}

	@Override
	public void delete(Invoice invoice) {
		// TODO Auto-generated method stub
		repository.delete(invoice);
	}

	@Override
	public Invoice getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

}
