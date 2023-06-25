package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.DetailAddMoney;

public interface DetailAddMoneyDao {
	public List<DetailAddMoney> getAll();
	
	public List<DetailAddMoney> getByidAccount(int idAccount);
	
	public void add(DetailAddMoney detailAddMoney);
	
	public void update(DetailAddMoney detailAddMoney);
	
	public void delete(DetailAddMoney detailAddMoney);
	
	public DetailAddMoney getByID(int id);
}
