package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ComboDao;
import com.example.TrungTamTA.Entity.Combo;
import com.example.TrungTamTA.Model.ComboDTO;
import com.example.TrungTamTA.Service.ComboService;

@Service
@Transactional
public class ComboServiceImpl implements ComboService{

	@Autowired ComboDao comboDao;
	
	private void getInfo(ComboDTO comboDTO, Combo combo) {
		comboDTO.setId(combo.getId());
		comboDTO.setName(combo.getName());
		comboDTO.setDiscount(combo.getDiscount());
		comboDTO.setQuantiityCourses(combo.getQuantiityCourses());
		comboDTO.setTotalPrice(combo.getTotalPrice());
		comboDTO.setNewTotalPrice(combo.getNewTotalPrice());
		comboDTO.setDescription(combo.getDescription());
		comboDTO.setCreatedDate(combo.getCreatedDate());
		comboDTO.setUpdatedDate(combo.getUpdatedDate());
	}
	
	private void setInfo(ComboDTO comboDTO, Combo combo) {
		combo.setName(comboDTO.getName());
		combo.setDiscount(comboDTO.getDiscount());
		combo.setQuantiityCourses(comboDTO.getQuantiityCourses());
		combo.setTotalPrice(comboDTO.getTotalPrice());
		combo.setNewTotalPrice(comboDTO.getNewTotalPrice());
		combo.setDescription(comboDTO.getDescription());
	}
	
	@Override
	public List<ComboDTO> getAll() {
		List<ComboDTO> comboDTOs = new ArrayList<ComboDTO>();
		List<Combo> combos = comboDao.getAll();
		
		for(Combo combo: combos) {
			ComboDTO comboDTO = new ComboDTO();
			getInfo(comboDTO, combo);
			comboDTOs.add(comboDTO);
		}
		return comboDTOs;
	}

	@Override
	public void add(ComboDTO comboDTO) {
		Combo combo = new Combo();
		setInfo(comboDTO, combo);
		comboDao.add(combo);
	}

	@Override
	public void update(ComboDTO comboDTO) {
		Combo combo = comboDao.getByID(comboDTO.getId());
		if(combo != null) {
			setInfo(comboDTO, combo);
			combo.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
			comboDao.update(combo);
		}
	}

	@Override
	public void delete(int id) {
		Combo combo = comboDao.getByID(id);
		if(combo != null) {
			comboDao.delete(combo);
		}
	}

	@Override
	public ComboDTO getByID(int id) {
		Combo combo = comboDao.getByID(id);
		if(combo != null) {
			ComboDTO comboDTO = new ComboDTO();
			getInfo(comboDTO, combo);
			return comboDTO;
		}
		return null;
	}

}
