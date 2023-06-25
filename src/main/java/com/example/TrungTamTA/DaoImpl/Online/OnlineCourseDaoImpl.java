package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.OnlineCourseDao;
import com.example.TrungTamTA.Entity.Online.OnlineCourse;
import com.example.TrungTamTA.Repository.Online.OnlineCourseRepository;

@Repository
public class OnlineCourseDaoImpl implements OnlineCourseDao{

	@Autowired OnlineCourseRepository repository;
	
	@Override
	public List<OnlineCourse> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(OnlineCourse course) {
		repository.save(course);
	}

	@Override
	public void update(OnlineCourse course) {
		repository.save(course);
	}

	@Override
	public void delete(OnlineCourse course) {
		repository.save(course);
	}

	@Override
	public OnlineCourse getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public List<OnlineCourse> getByidCourse(int idCourse) {
		return repository.findByidCourse(idCourse);
	}

	@Override
	public List<OnlineCourse> getByidTeacher(int idTeacher) {
		return repository.findByidTeacher(idTeacher);
	}

	@Override
	public List<OnlineCourse> getByidDiscount(int idDiscount) {
		return repository.findByidDiscount(idDiscount);
	}

}
