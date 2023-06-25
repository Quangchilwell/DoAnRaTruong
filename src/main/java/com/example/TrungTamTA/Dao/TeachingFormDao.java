package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.TeachingForm;

public interface TeachingFormDao {
	public List<TeachingForm> getAll();
	
	public void add(TeachingForm teachingForm);
	
	public void update(TeachingForm teachingForm);
	
	public void delete(TeachingForm teachingForm);
	
	public TeachingForm getByID(int id);
	
}
