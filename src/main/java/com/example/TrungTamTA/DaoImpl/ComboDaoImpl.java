package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ComboDao;
import com.example.TrungTamTA.Entity.Combo;
import com.example.TrungTamTA.Repository.ComboRepository;

@Repository
@Transactional
public class ComboDaoImpl implements ComboDao{

	@Autowired ComboRepository repository;
	
	@Override
	public List<Combo> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(Combo combo) {
		// TODO Auto-generated method stub
		repository.save(combo);
	}

	@Override
	public void update(Combo combo) {
		// TODO Auto-generated method stub
		repository.save(combo);
	}

	@Override
	public void delete(Combo combo) {
		// TODO Auto-generated method stub
		repository.delete(combo);
	}

	@Override
	public Combo getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

}
