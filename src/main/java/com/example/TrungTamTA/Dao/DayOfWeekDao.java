package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.DayOfWeek;

public interface DayOfWeekDao {
	public List<DayOfWeek> getAll();
	
	public void add(DayOfWeek dayOfWeek);
	
	public void update(DayOfWeek dayOfWeek);
	
	public void delete(DayOfWeek dayOfWeek);
	
	public DayOfWeek getById(int id);
}
