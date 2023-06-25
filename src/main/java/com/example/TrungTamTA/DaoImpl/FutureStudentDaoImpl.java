package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.FutureStudentDao;
import com.example.TrungTamTA.Entity.FutureStudent;
import com.example.TrungTamTA.Repository.FutureStudentRepository;

@Repository

public class FutureStudentDaoImpl implements FutureStudentDao {

	@Autowired
	FutureStudentRepository repository;

	@Override
	public List<FutureStudent> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(FutureStudent futureStudent) {
		repository.save(futureStudent);
	}

	@Override
	public void update(FutureStudent futureStudent) {
		repository.save(futureStudent);
	}

	@Override
	public void delete(FutureStudent futureStudent) {
		repository.delete(futureStudent);
	}

	@Override
	public FutureStudent getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public List<FutureStudent> getBystatus(int status) {
		return repository.findBystatus(status);
	}

}
