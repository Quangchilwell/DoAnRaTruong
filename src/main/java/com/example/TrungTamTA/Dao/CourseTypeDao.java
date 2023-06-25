package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.CourseType;

public interface CourseTypeDao {
	public List<CourseType> getAll();
	
	public void add(CourseType courseType);
	
	public void update(CourseType courseType);
	
	public void delete(CourseType courseType);
	
	public CourseType getByID(int id);
}
