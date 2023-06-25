package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.StudentDetailDao;
import com.example.TrungTamTA.Entity.StudentDetail;
import com.example.TrungTamTA.Repository.StudentDetailRepository;

@Repository
@Transactional
public class StudentDetailDaoImpl implements StudentDetailDao{

	@Autowired StudentDetailRepository repository;
	
	@Override
	public List<StudentDetail> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<StudentDetail> getByidStudent(int idStudent) {
		// TODO Auto-generated method stub
		return repository.findByidStudent(idStudent);
	}

	@Override
	public List<StudentDetail> getByidClass(int idClass) {
		// TODO Auto-generated method stub
		return repository.findByidClass(idClass);
	}

	@Override
	public void add(StudentDetail studentDetail) {
		repository.save(studentDetail);
	}

	@Override
	public void update(StudentDetail studentDetail) {
		repository.save(studentDetail);
	}

	@Override
	public void delete(StudentDetail studentDetail) {
		repository.delete(studentDetail);
	}

	@Override
	public StudentDetail getByID(int id) {
		return repository.findByid(id);
	}

}
