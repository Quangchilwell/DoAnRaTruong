package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.DetailAddMoneyDao;
import com.example.TrungTamTA.Entity.Online.DetailAddMoney;
import com.example.TrungTamTA.Repository.Online.DetailAddMoneyRepository;

@Repository
public class DetailAddMoneyDaoImpl implements DetailAddMoneyDao{

	@Autowired DetailAddMoneyRepository repository;
	
	@Override
	public List<DetailAddMoney> getAll() {
		return repository.findAll();
	}

	@Override
	public List<DetailAddMoney> getByidAccount(int idAccount) {
		return repository.findByidAccount(idAccount);
	}

	@Override
	public void add(DetailAddMoney detailAddMoney) {
		repository.save(detailAddMoney);
	}

	@Override
	public void update(DetailAddMoney detailAddMoney) {
		repository.save(detailAddMoney);
	}

	@Override
	public void delete(DetailAddMoney detailAddMoney) {
		repository.delete(detailAddMoney);
	}

	@Override
	public DetailAddMoney getByID(int id) {
		return repository.findByid(id);
	}

}
