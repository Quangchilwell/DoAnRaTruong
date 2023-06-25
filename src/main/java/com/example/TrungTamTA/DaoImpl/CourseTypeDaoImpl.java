package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.CourseTypeDao;
import com.example.TrungTamTA.Entity.CourseType;
import com.example.TrungTamTA.Repository.CourseTypeRepository;

@Repository
@Transactional
public class CourseTypeDaoImpl implements CourseTypeDao{

	@Autowired
	CourseTypeRepository repository;
	
	@Override
	public List<CourseType> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(CourseType courseType) {
		// TODO Auto-generated method stub
		repository.save(courseType);
	}

	@Override
	public void update(CourseType courseType) {
		// TODO Auto-generated method stub
		repository.save(courseType);
	}

	@Override
	public void delete(CourseType courseType) {
		// TODO Auto-generated method stub
		repository.delete(courseType);
	}

	@Override
	public CourseType getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

}
