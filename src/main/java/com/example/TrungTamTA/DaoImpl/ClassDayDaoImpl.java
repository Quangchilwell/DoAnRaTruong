package com.example.TrungTamTA.DaoImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ClassDayDao;
import com.example.TrungTamTA.Entity.ClassDay;
import com.example.TrungTamTA.Repository.ClassDayRepository;

@Repository
public class ClassDayDaoImpl implements ClassDayDao{

	@Autowired ClassDayRepository repository;
	
	@Override
	public List<ClassDay> getAll() {
		return repository.findAll();
	}

	@Override
	public List<ClassDay> getByidClass(int idClass) {
		return repository.findByidClassOpening(idClass);
	}

	@Override
	public void add(ClassDay classDay) {
		repository.save(classDay);
	}

	@Override
	public void update(ClassDay classDay) {
		repository.save(classDay);
	}

	@Override
	public void delete(ClassDay classDay) {
		repository.delete(classDay);
	}

	@Override
	public ClassDay getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public ClassDay checkClassCompleted(int idClass, Date date) {
		return repository.checkClassCompleted(idClass, date);
	}

	@Override
	public List<ClassDay> getClassDaysWereCompleted(int idClass) {
		return repository.getClassDaysWereCompleted(idClass);
	}

	@Override
	public List<ClassDay> getClassDaysWerePostPone(int idClass) {
		return repository.getClassDaysWerePostPone(idClass);
	}

}
