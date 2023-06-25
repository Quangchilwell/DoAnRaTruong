package com.example.TrungTamTA.ServiceImpl.Online;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.Online.DiscountCodeDao;
import com.example.TrungTamTA.Entity.Online.DiscountCode;
import com.example.TrungTamTA.Model.Online.DiscountCodeDTO;
import com.example.TrungTamTA.Service.Online.DiscountCodeService;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService{

	@Autowired DiscountCodeDao dao;
	
	private void getInfo(DiscountCodeDTO dto, DiscountCode discountCode) {
		dto.setId(discountCode.getId());
		dto.setName(discountCode.getName());
		dto.setDiscountPercent(discountCode.getDiscountPercent());
		dto.setDiscountPrice(discountCode.getDiscountPrice());
		dto.setCreatedAt(discountCode.getCreatedAt());
		dto.setUpdatedAt(discountCode.getUpdatedAt());
	}
	
	private void setInfo(DiscountCodeDTO dto, DiscountCode discountCode) {
		discountCode.setName(dto.getName());
		discountCode.setDiscountPrice(dto.getDiscountPrice());
		discountCode.setDiscountPercent(dto.getDiscountPercent());
	}
	
	@Override
	public List<DiscountCodeDTO> getAll() {
		List<DiscountCodeDTO> dtos = new ArrayList<DiscountCodeDTO>();
		List<DiscountCode> codes = dao.getAll();
		
		for(DiscountCode code: codes) {
			DiscountCodeDTO discountCodeDTO = new DiscountCodeDTO();
			getInfo(discountCodeDTO, code);
			dtos.add(discountCodeDTO);
		}
		return dtos;
	}

	@Override
	public void add(DiscountCodeDTO discountCodeDTO) {
		DiscountCode discountCode = new DiscountCode();
		setInfo(discountCodeDTO, discountCode);
		dao.add(discountCode);
	}

	@Override
	public void update(DiscountCodeDTO discountCodeDTO) {
		DiscountCode discountCode = dao.getByID(discountCodeDTO.getId());
		if(discountCode != null) {
			setInfo(discountCodeDTO, discountCode);
			discountCode.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			dao.update(discountCode);
		}
	}

	@Override
	public void delete(int id) {
		DiscountCode discountCode = dao.getByID(id);
		if(discountCode != null) {
			dao.delete(discountCode);
		}
	}

	@Override
	public DiscountCodeDTO getByID(int id) {
		DiscountCode discountCode = dao.getByID(id);
		if(discountCode != null) {
			DiscountCodeDTO dto = new DiscountCodeDTO();
			getInfo(dto, discountCode);
			return dto;
			
		}
		return null;
	}
	
}
