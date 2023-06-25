package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.ClassType;

public interface ClassTypeDao {
	public List<ClassType> getAll();

	public void add(ClassType classType);

	public void update(ClassType classType);

	public void delete(ClassType classType);

	public ClassType getByID(int id);
}
