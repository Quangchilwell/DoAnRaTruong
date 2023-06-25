package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.Tutor;

public interface TutorDao {
	public List<Tutor> getAll();
	
	public void add(Tutor tutor);
	
	public void update(Tutor tutor);
	
	public void delete(Tutor tutor);
	
	public Tutor getByID(int id);
}
