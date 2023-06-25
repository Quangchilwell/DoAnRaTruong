package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.CourseDao;
import com.example.TrungTamTA.Entity.Course;
import com.example.TrungTamTA.Entity.RegisterCourse;
import com.example.TrungTamTA.Repository.CourseRepository;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao{

	@Autowired
	CourseRepository repository;
	
	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Course> getByIdType(int idType) {
		// TODO Auto-generated method stub
		return repository.findByidCourseType(idType);
	}

	@Override
	public List<Course> getByIdForm(int idForm) {
		// TODO Auto-generated method stub
		return repository.findByidTeachingForm(idForm);
	}

	@Override
	public void add(Course course) {
		// TODO Auto-generated method stub
		repository.save(course);
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		repository.save(course);
	}

	@Override
	public void delete(Course course) {
		// TODO Auto-generated method stub
		repository.delete(course);
	}

	@Override
	public Course getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

	@Override
	public List<Course> getCoursesNotInCombo(int idCombo) {
		// TODO Auto-generated method stub
		return repository.getCoursesNotInCombo(idCombo);
	}

	@Override
	public List<Course> getOfflineCourses() {
		// TODO Auto-generated method stub
		return repository.getOfflineCourses();
	}

	@Override
	public List<Course> getOnlinelineCourses() {
		// TODO Auto-generated method stub
		return repository.getOnlineCourses();
	}

}
