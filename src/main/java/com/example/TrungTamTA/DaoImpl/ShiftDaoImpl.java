package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ShiftDao;
import com.example.TrungTamTA.Entity.Shift;
import com.example.TrungTamTA.Repository.ShiftRepository;

@Repository
public class ShiftDaoImpl implements ShiftDao{

	@Autowired ShiftRepository repository;
	
	@Override
	public List<Shift> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(Shift shift) {
		repository.save(shift);	
	}

	@Override
	public void update(Shift shift) {
		repository.save(shift);	
	}

	@Override
	public void delete(Shift shift) {
		repository.delete(shift);	
	}

	@Override
	public Shift getByID(int id) {
		return repository.findByid(id);
	}
	
}
