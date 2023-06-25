package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.ComboDetail;

public interface ComboDetailDao {
	public List<ComboDetail> getAll();
	
	public List<ComboDetail> getByidCourse(int idCourse);
	
	public List<ComboDetail> getByidCombo(int idCombo);
	
	public void add(ComboDetail comboDetail);
	
	public void update(ComboDetail comboDetail);
	
	public void delete(ComboDetail comboDetail);
	
	public ComboDetail getByID(int id);
}
