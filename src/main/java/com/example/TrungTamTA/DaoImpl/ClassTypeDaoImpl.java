package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ClassTypeDao;
import com.example.TrungTamTA.Entity.ClassType;
import com.example.TrungTamTA.Repository.ClassTypeRepository;

@Repository
public class ClassTypeDaoImpl implements ClassTypeDao {
	@Autowired
	ClassTypeRepository repository;

	@Override
	public List<ClassType> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(ClassType classType) {
		repository.save(classType);
	}

	@Override
	public void update(ClassType classType) {
		repository.save(classType);
	}

	@Override
	public void delete(ClassType classType) {
		repository.delete(classType);
	}

	@Override
	public ClassType getByID(int id) {
		return repository.findByid(id);
	}
}
