package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ComboDetailDao;
import com.example.TrungTamTA.Entity.ComboDetail;
import com.example.TrungTamTA.Repository.ComboDetailRepository;

@Repository
@Transactional
public class ComboDetailDaoImpl implements ComboDetailDao{

	@Autowired ComboDetailRepository repository;
	
	@Override
	public List<ComboDetail> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<ComboDetail> getByidCourse(int idCourse) {
		return repository.findByidCourse(idCourse);
	}

	@Override
	public List<ComboDetail> getByidCombo(int idCombo) {
		return repository.getByidCombo(idCombo);
	}

	@Override
	public void add(ComboDetail comboDetail) {
		repository.save(comboDetail);
	}

	@Override
	public void update(ComboDetail comboDetail) {
		repository.save(comboDetail);
	}

	@Override
	public void delete(ComboDetail comboDetail) {
		repository.delete(comboDetail);
	}

	@Override
	public ComboDetail getByID(int id) {
		return repository.findByid(id);
	}

}
