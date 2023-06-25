package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Shift;

public interface ShiftDao {
	public List<Shift> getAll();
	
	public void add(Shift shift);
	
	public void update(Shift shift);
	
	public void delete(Shift shift);
	
	public Shift getByID(int id);
}
