package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Model.Online.AccountStudentDTO;

public interface AccountStudentService {
	public List<AccountStudentDTO> getAll();
	
	public void add(AccountStudentDTO accountStudentDTO);
	
	public void update(AccountStudentDTO accountStudentDTO);
	
	public void delete(int id);
	
	public AccountStudentDTO getById(int id);
	
	public AccountStudentDTO getByEmail(String email);
}
