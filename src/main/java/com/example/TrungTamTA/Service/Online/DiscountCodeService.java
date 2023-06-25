package com.example.TrungTamTA.Service.Online;

import java.util.List;

import com.example.TrungTamTA.Model.Online.DiscountCodeDTO;

public interface DiscountCodeService {
	public List<DiscountCodeDTO> getAll();
	
	public void add(DiscountCodeDTO discountCodeDTO);
	
	public void update(DiscountCodeDTO discountCodeDTO);
	
	public void delete(int id);
	
	public DiscountCodeDTO getByID(int id);
}
