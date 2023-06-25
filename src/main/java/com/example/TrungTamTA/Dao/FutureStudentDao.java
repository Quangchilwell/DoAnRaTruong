package com.example.TrungTamTA.Dao;

import java.util.List;

import com.example.TrungTamTA.Entity.FutureStudent;

public interface FutureStudentDao {
	public List<FutureStudent> getAll();

	public List<FutureStudent> getBystatus(int status);

	public void add(FutureStudent futureStudent);

	public void update(FutureStudent futureStudent);

	public void delete(FutureStudent futureStudent);

	public FutureStudent getByID(int id);
}
