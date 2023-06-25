package com.example.TrungTamTA.ServiceImpl.Online;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.AccountStudentDao;
import com.example.TrungTamTA.Entity.Online.AccountStudent;
import com.example.TrungTamTA.Model.Online.AccountStudentDTO;
import com.example.TrungTamTA.Service.Online.AccountStudentService;

@Service
@Transactional
public class AccountStudentServiceImpl implements AccountStudentService{

	@Autowired AccountStudentDao dao;
	
	private void getInfo(AccountStudentDTO dto, AccountStudent accountStudent) {
		dto.setId(accountStudent.getId());
		dto.setName(accountStudent.getName());
		dto.setEmail(accountStudent.getEmail());
		dto.setMoneyAvailable(accountStudent.getMoneyAvailable());
		dto.setCreatedAt(accountStudent.getCreatedAt());
		dto.setUpdatedAt(accountStudent.getUpdatedAt());
		dto.setNote(accountStudent.getNote());
		dto.setPassword(accountStudent.getPassword());
		dto.setPhone(accountStudent.getPhone());
	} 
	
	private void setInfo(AccountStudentDTO dto, AccountStudent accountStudent) {
		accountStudent.setName(dto.getName());
		accountStudent.setEmail(dto.getEmail());
		accountStudent.setMoneyAvailable(dto.getMoneyAvailable());
		accountStudent.setNote(dto.getNote());
		accountStudent.setPassword(dto.getPassword());
		accountStudent.setPhone(dto.getPhone());
	}
	
	@Override
	public List<AccountStudentDTO> getAll() {
		List<AccountStudentDTO> accountStudentDTOs = new ArrayList<AccountStudentDTO>();
		List<AccountStudent> accountStudents = dao.getAll();
		
		for(AccountStudent ac: accountStudents) {
			AccountStudentDTO dto = new AccountStudentDTO();
			getInfo(dto, ac);
			accountStudentDTOs.add(dto);
		}
		return accountStudentDTOs;
	}

	@Override
	public void add(AccountStudentDTO accountStudentDTO) {
		AccountStudent accountStudent = new AccountStudent();
		setInfo(accountStudentDTO, accountStudent);
		dao.add(accountStudent);
	}

	@Override
	public void update(AccountStudentDTO accountStudentDTO) {
		AccountStudent accountStudent = dao.getById(accountStudentDTO.getId());
		if(accountStudent != null) {
			setInfo(accountStudentDTO, accountStudent);
			accountStudent.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			dao.update(accountStudent);
		}
	}

	@Override
	public void delete(int id) {
		AccountStudent accountStudent = dao.getById(id);
		if(accountStudent != null) {
			dao.delete(accountStudent);
		}
	}

	@Override
	public AccountStudentDTO getById(int id) {
		AccountStudent accountStudent = dao.getById(id);
		if(accountStudent != null) {
			AccountStudentDTO dto = new AccountStudentDTO();
			getInfo(dto, accountStudent);
			return dto;
		}
		return null;
	}

	@Override
	public AccountStudentDTO getByEmail(String email) {
		AccountStudent accountStudent = dao.getByEmail(email);
		if(accountStudent != null) {
			AccountStudentDTO dto = new AccountStudentDTO();
			getInfo(dto, accountStudent);
			return dto;
		}
		return null;
	}

}
