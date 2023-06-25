package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.TutorDao;
import com.example.TrungTamTA.Entity.Tutor;
import com.example.TrungTamTA.Repository.TutorRepository;

@Repository
@Transactional
public class TutorDaoImpl implements TutorDao{
	
	@Autowired TutorRepository repository;

	@Override
	public List<Tutor> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(Tutor tutor) {
		repository.save(tutor);
	}

	@Override
	public void update(Tutor tutor) {
		repository.save(tutor);
	}

	@Override
	public void delete(Tutor tutor) {
		repository.delete(tutor);
	}

	@Override
	public Tutor getByID(int id) {
		return repository.findByid(id);
	}
	
	
}
