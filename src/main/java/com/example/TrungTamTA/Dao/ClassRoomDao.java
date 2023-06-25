package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.ClassRoom;

public interface ClassRoomDao {
	public List<ClassRoom> getAll();
	
	public void add(ClassRoom classRoom);
	
	public void update(ClassRoom classRoom);
	
	public void delete(ClassRoom classRoom);
	
	public ClassRoom getByID(int id);
	
	public List<ClassRoom> getByIdType(int idType);
}
