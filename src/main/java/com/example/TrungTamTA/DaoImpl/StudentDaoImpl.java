package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.StudentDao;
import com.example.TrungTamTA.Entity.Student;
import com.example.TrungTamTA.Repository.StudentRepository;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{
	
	@Autowired
	StudentRepository repository;

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(Student student) {
		// TODO Auto-generated method stub
		repository.save(student);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		repository.save(student);
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		repository.delete(student);
	}

	@Override
	public Student getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}
	
	
}
