package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.AccountStudent;

public interface AccountStudentDao {
	
	public List<AccountStudent> getAll();
	
	public void add(AccountStudent accountStudent);
	
	public void update(AccountStudent accountStudent);
	
	public void delete(AccountStudent accountStudent);
	
	public AccountStudent getById(int id);
	
	public AccountStudent getByEmail(String email);
	
}
