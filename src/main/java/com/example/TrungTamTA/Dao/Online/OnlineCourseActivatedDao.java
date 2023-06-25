package com.example.TrungTamTA.Dao.Online;

import java.util.List;

import com.example.TrungTamTA.Entity.Online.OnlineCourseActivated;

public interface OnlineCourseActivatedDao {
	public List<OnlineCourseActivated> getAll();
	
	public List<OnlineCourseActivated> getByidAccount(int idAccount);
	
	public List<OnlineCourseActivated> getByidOnlineCourse(int idOnlineCourse);
	
	public void add(OnlineCourseActivated activated);
	
	public void update(OnlineCourseActivated activated);
	
	public void delete(OnlineCourseActivated activated);
	
	public OnlineCourseActivated getByID(int id);
}
