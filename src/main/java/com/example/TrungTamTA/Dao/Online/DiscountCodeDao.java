package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.DiscountCode;

public interface DiscountCodeDao {
	public List<DiscountCode> getAll();
	
	public void add(DiscountCode discountCode);
	
	public void update(DiscountCode discountCode);
	
	public void delete(DiscountCode discountCode);
	
	public DiscountCode getByID(int id);
	
}
