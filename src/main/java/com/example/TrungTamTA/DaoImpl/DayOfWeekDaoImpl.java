package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.DayOfWeekDao;
import com.example.TrungTamTA.Entity.DayOfWeek;
import com.example.TrungTamTA.Repository.DayOfWeekReporitory;

@Repository
@Transactional
public class DayOfWeekDaoImpl implements DayOfWeekDao{

	@Autowired DayOfWeekReporitory reporitory;
	
	@Override
	public List<DayOfWeek> getAll() {
		return reporitory.findAll();
	}

	@Override
	public void add(DayOfWeek dayOfWeek) {
		reporitory.save(dayOfWeek);
	}

	@Override
	public void update(DayOfWeek dayOfWeek) {
		reporitory.save(dayOfWeek);
	}

	@Override
	public void delete(DayOfWeek dayOfWeek) {
		reporitory.delete(dayOfWeek);
	}

	@Override
	public DayOfWeek getById(int id) {
		return reporitory.findByid(id);
	}
	
}
