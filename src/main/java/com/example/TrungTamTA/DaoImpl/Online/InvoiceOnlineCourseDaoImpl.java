package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.InvoiceOnlineCourseDao;
import com.example.TrungTamTA.Entity.Online.InvoiceOnlineCourse;
import com.example.TrungTamTA.Repository.Online.InvoiceOnlineCourseRepository;

@Repository
public class InvoiceOnlineCourseDaoImpl implements InvoiceOnlineCourseDao{

	@Autowired InvoiceOnlineCourseRepository repository;
	
	@Override
	public List<InvoiceOnlineCourse> getAll() {
		return repository.findAll();
	}

	@Override
	public List<InvoiceOnlineCourse> getByidAccount(int idAccount) {
		return repository.findByidAccount(idAccount);
	}

	@Override
	public List<InvoiceOnlineCourse> getByidOnlineCourse(int idOnlineCourse) {
		return repository.findByidOnlineCourse(idOnlineCourse);
	}

	@Override
	public void add(InvoiceOnlineCourse invoiceOnlineCourse) {
		repository.save(invoiceOnlineCourse);
	}

	@Override
	public void update(InvoiceOnlineCourse invoiceOnlineCourse) {
		repository.save(invoiceOnlineCourse);
	}

	@Override
	public void delete(InvoiceOnlineCourse invoiceOnlineCourse) {
		repository.delete(invoiceOnlineCourse);
	}

	@Override
	public InvoiceOnlineCourse getByID(int id) {
		return repository.findByid(id);
	}
	
}
