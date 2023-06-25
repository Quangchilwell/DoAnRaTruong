package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.AccountStudentDao;
import com.example.TrungTamTA.Entity.Online.AccountStudent;
import com.example.TrungTamTA.Repository.Online.AccountStudentRepository;

@Repository
public class AccountStudentDaoImpl implements AccountStudentDao{

	@Autowired AccountStudentRepository repository;
	
	@Override
	public List<AccountStudent> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(AccountStudent accountStudent) {
		repository.save(accountStudent);
	}

	@Override
	public void update(AccountStudent accountStudent) {
		repository.save(accountStudent);
	}

	@Override
	public void delete(AccountStudent accountStudent) {
		repository.delete(accountStudent);
	}

	@Override
	public AccountStudent getById(int id) {
		return repository.findByid(id);
	}

	@Override
	public AccountStudent getByEmail(String email) {
		return repository.findByemail(email);
	}

}
