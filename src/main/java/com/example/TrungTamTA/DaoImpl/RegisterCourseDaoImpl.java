package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.RegisterCourseDao;
import com.example.TrungTamTA.Entity.RegisterCourse;
import com.example.TrungTamTA.Repository.RegisterCourseRepository;

@Repository
@Transactional
public class RegisterCourseDaoImpl implements RegisterCourseDao{

	@Autowired RegisterCourseRepository repository;
	
	@Override
	public List<RegisterCourse> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<RegisterCourse> getByIdCourse(int idCourse) {
		// TODO Auto-generated method stub
		return repository.findByidCourse(idCourse);
	}

	@Override
	public List<RegisterCourse> getByIdStudent(int idStudent) {
		// TODO Auto-generated method stub
		return repository.findByidStudent(idStudent);
	}
	
	@Override
	public List<RegisterCourse> getByIdClassOpening(int idClass) {
		return repository.findByidClassOpening(idClass);
	}

	@Override
	public void add(RegisterCourse registerCourse) {
		// TODO Auto-generated method stub
		repository.save(registerCourse);
	}
	
	@Override
	public void addMany(List<RegisterCourse> registerCourses) {
		// TODO Auto-generated method stub
		repository.saveAll(registerCourses);
	}

	@Override
	public void update(RegisterCourse registerCourse) {
		// TODO Auto-generated method stub
		repository.save(registerCourse);
	}

	@Override
	public void delete(RegisterCourse registerCourse) {
		// TODO Auto-generated method stub
		repository.delete(registerCourse);
	}

	@Override
	public RegisterCourse getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

	@Override
	public List<RegisterCourse> getRegistersHaveNotClass() {
		return repository.getRegistersHaveNotClass();
	}
	
	@Override
	public List<RegisterCourse> getStudentsInClass(int idClass) {
		return repository.getStudentsInClass(idClass);
	}

	@Override
	public List<RegisterCourse> getRegistersCanOpenClass(int idCourse) {
		return repository.getRegistersCanOpenClass(idCourse);
	}

	@Override
	public List<RegisterCourse> getByIdClassOpeningAndSoftDelete(int idClass, int softDelete) {
		return repository.findByidClassOpeningAndSoftDelete(idClass, softDelete);
	}
}
