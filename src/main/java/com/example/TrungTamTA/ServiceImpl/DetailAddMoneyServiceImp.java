package com.example.TrungTamTA.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.DetailAddMoneyDao;
import com.example.TrungTamTA.Entity.Online.DetailAddMoney;
import com.example.TrungTamTA.Model.Online.AccountStudentDTO;
import com.example.TrungTamTA.Model.Online.DetailAddMoneyDTO;
import com.example.TrungTamTA.Service.Online.AccountStudentService;
import com.example.TrungTamTA.Service.Online.DetailAddMoneyService;

@Service
public class DetailAddMoneyServiceImp implements DetailAddMoneyService{

	@Autowired DetailAddMoneyDao dao;
	@Autowired AccountStudentService acService;
	
	private void getInfo(DetailAddMoneyDTO dto, DetailAddMoney deMoney) {
		AccountStudentDTO acDto = acService.getById(deMoney.getIdAccount());
		
		dto.setId(deMoney.getId());
		dto.setAccountStudentDTO(acDto);
		dto.setMoney(deMoney.getMoney());
		dto.setCreatedAt(deMoney.getCreatedAt());
	}
	
	private void setInfo(DetailAddMoneyDTO dto, DetailAddMoney deMoney) {
		deMoney.setMoney(dto.getMoney());
		deMoney.setIdAccount(dto.getAccountStudentDTO().getId());
	}
	
	@Override
	public List<DetailAddMoneyDTO> getAll() {
		List<DetailAddMoney> detailAddMoneys = dao.getAll();
		List<DetailAddMoneyDTO> dtos = new ArrayList<DetailAddMoneyDTO>();
		
		for(DetailAddMoney detailAddMoney: detailAddMoneys) {
			DetailAddMoneyDTO dto = new DetailAddMoneyDTO();
			getInfo(dto, detailAddMoney);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<DetailAddMoneyDTO> getByidAccount(int idAccount) {
		List<DetailAddMoney> detailAddMoneys = dao.getByidAccount(idAccount);
		List<DetailAddMoneyDTO> dtos = new ArrayList<DetailAddMoneyDTO>();
		
		for(DetailAddMoney detailAddMoney: detailAddMoneys) {
			DetailAddMoneyDTO dto = new DetailAddMoneyDTO();
			getInfo(dto, detailAddMoney);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void add(DetailAddMoneyDTO detailAddMoneyDTO) {
		DetailAddMoney detailAddMoney = new DetailAddMoney();
		setInfo(detailAddMoneyDTO, detailAddMoney);
		dao.add(detailAddMoney);
	}

	@Override
	public void update(DetailAddMoneyDTO detailAddMoneyDTO) {
		DetailAddMoney detailAddMoney = dao.getByID(detailAddMoneyDTO.getId());
		if(detailAddMoney != null) {
			setInfo(detailAddMoneyDTO, detailAddMoney);
			dao.update(detailAddMoney);
		}
	}

	@Override
	public void delete(int id) {
		DetailAddMoney detailAddMoney = dao.getByID(id);
		if(detailAddMoney != null) {
			dao.delete(detailAddMoney);
		}
	}

	@Override
	public DetailAddMoneyDTO getByID(int id) {
		DetailAddMoney detailAddMoney = dao.getByID(id);
		if(detailAddMoney != null) {
			DetailAddMoneyDTO dto = new DetailAddMoneyDTO();
			getInfo(dto, detailAddMoney);
			return dto;
		}
		return null;
	}

}
