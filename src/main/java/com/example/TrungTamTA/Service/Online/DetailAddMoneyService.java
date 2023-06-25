package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Model.Online.DetailAddMoneyDTO;

public interface DetailAddMoneyService {
	public List<DetailAddMoneyDTO> getAll();
	
	public List<DetailAddMoneyDTO> getByidAccount(int idAccount);
	
	public void add(DetailAddMoneyDTO detailAddMoneyDTO);
	
	public void update(DetailAddMoneyDTO detailAddMoneyDTO);
	
	public void delete(int id);
	
	public DetailAddMoneyDTO getByID(int id);
}
