package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.TeachingFormDao;
import com.example.TrungTamTA.Entity.TeachingForm;
import com.example.TrungTamTA.Repository.TeacingFormRepository;

@Repository
@Transactional
public class TeachingFormDaoImpl implements TeachingFormDao{

	@Autowired
	TeacingFormRepository repository;

	@Override
	public List<TeachingForm> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(TeachingForm teachingForm) {
		// TODO Auto-generated method stub
		repository.save(teachingForm);
	}

	@Override
	public void update(TeachingForm teachingForm) {
		// TODO Auto-generated method stub
		repository.save(teachingForm);
	}

	@Override
	public void delete(TeachingForm teachingForm) {
		// TODO Auto-generated method stub
		repository.delete(teachingForm);
	}

	@Override
	public TeachingForm getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}
	
	
}
