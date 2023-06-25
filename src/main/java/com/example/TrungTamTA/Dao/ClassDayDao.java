package com.example.TrungTamTA.Dao;

import java.sql.Date;
import java.util.List;

import com.example.TrungTamTA.Entity.ClassDay;

public interface ClassDayDao {
	public List<ClassDay> getAll();

	public List<ClassDay> getByidClass(int idClass);

	public List<ClassDay> getClassDaysWereCompleted(int idClass);

	public List<ClassDay> getClassDaysWerePostPone(int idClass);

	public void add(ClassDay classDay);

	public void update(ClassDay classDay);

	public void delete(ClassDay classDay);

	public ClassDay getByID(int id);

	public ClassDay checkClassCompleted(int idClass, Date date);
}
