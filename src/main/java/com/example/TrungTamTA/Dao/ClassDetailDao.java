package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.ClassDetail;

public interface ClassDetailDao {
	public List<ClassDetail> getAll();
	
	public List<ClassDetail> getByidDayOfWeek(int idDayOfWeek);
	
	public List<ClassDetail> getByidClassOpening(int idClassOpening);
	
	public void add(ClassDetail classDetail);
	
	public void update(ClassDetail classDetail);
	
	public void delete(ClassDetail classDetail);
	
	public ClassDetail getByID(int id);
}
