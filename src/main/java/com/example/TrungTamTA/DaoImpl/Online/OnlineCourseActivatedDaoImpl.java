package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.OnlineCourseActivatedDao;
import com.example.TrungTamTA.Entity.Online.OnlineCourseActivated;
import com.example.TrungTamTA.Repository.Online.OnlineCourseActivatedRepository;

@Repository
public class OnlineCourseActivatedDaoImpl implements OnlineCourseActivatedDao{

	@Autowired OnlineCourseActivatedRepository repository;
	
	@Override
	public List<OnlineCourseActivated> getAll() {
		return repository.findAll();
	}

	@Override
	public List<OnlineCourseActivated> getByidAccount(int idAccount) {
		return repository.findByidAccount(idAccount);
	}

	@Override
	public List<OnlineCourseActivated> getByidOnlineCourse(int idOnlineCourse) {
		return repository.findByidOnlineCourse(idOnlineCourse);
	}

	@Override
	public void add(OnlineCourseActivated activated) {
		repository.save(activated);
	}

	@Override
	public void update(OnlineCourseActivated activated) {
		repository.save(activated);
	}

	@Override
	public void delete(OnlineCourseActivated activated) {
		repository.delete(activated);
	}

	@Override
	public OnlineCourseActivated getByID(int id) {
		return repository.findByid(id);
	}

}
