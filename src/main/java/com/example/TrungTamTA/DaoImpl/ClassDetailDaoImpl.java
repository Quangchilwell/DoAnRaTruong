package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ClassDetailDao;
import com.example.TrungTamTA.Entity.ClassDetail;
import com.example.TrungTamTA.Repository.ClassDetailRepository;

@Repository
public class ClassDetailDaoImpl implements ClassDetailDao{

	@Autowired ClassDetailRepository repository;
	
	@Override
	public List<ClassDetail> getAll() {
		return repository.findAll();
	}

	
	@Override
	public List<ClassDetail> getByidDayOfWeek(int idDayOfWeek) {
		return repository.findByidDayOfWeek(idDayOfWeek);
	}

	@Override
	public List<ClassDetail> getByidClassOpening(int idClassOpening) {
		return repository.findByidClassOpening(idClassOpening);
	}


	@Override
	public void add(ClassDetail classDetail) {
		repository.save(classDetail);
	}

	@Override
	public void update(ClassDetail classDetail) {
		repository.save(classDetail);
	}

	@Override
	public void delete(ClassDetail classDetail) {
		repository.delete(classDetail);
	}

	@Override
	public ClassDetail getByID(int id) {
		return repository.findByid(id);
	}

}
