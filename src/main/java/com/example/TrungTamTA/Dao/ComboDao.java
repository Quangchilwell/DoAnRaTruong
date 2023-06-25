package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Combo;

public interface ComboDao {
	public List<Combo> getAll();
	
	public void add(Combo combo);
	
	public void update(Combo combo);
	
	public void delete(Combo combo);
	
	public Combo getByID(int id);
}
