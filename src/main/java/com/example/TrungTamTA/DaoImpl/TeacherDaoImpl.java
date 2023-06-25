package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.TeacherDao;
import com.example.TrungTamTA.Entity.Teacher;
import com.example.TrungTamTA.Repository.TeacherRepository;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao{

	@Autowired
	TeacherRepository repository;
	
	@Override
	public List<Teacher> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(Teacher teacher) {
		repository.save(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		repository.save(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		repository.delete(teacher);
	}

	@Override
	public Teacher getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public List<Teacher> getTeacherForOnlineCourse(int idCourse) {
		return repository.getTeacherForOnlineCourse(idCourse);
	}

}
