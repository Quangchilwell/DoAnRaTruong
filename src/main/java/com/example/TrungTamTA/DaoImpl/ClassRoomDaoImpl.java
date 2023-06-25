package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ClassRoomDao;
import com.example.TrungTamTA.Entity.ClassRoom;
import com.example.TrungTamTA.Repository.ClassRoomRepository;

@Repository
public class ClassRoomDaoImpl implements ClassRoomDao{

	@Autowired
	ClassRoomRepository repository;
	
	@Override
	public List<ClassRoom> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(ClassRoom classRoom) {
		// TODO Auto-generated method stub
		repository.save(classRoom);
	}

	@Override
	public void update(ClassRoom classRoom) {
		// TODO Auto-generated method stub
		repository.save(classRoom);
	}

	@Override
	public void delete(ClassRoom classRoom) {
		// TODO Auto-generated method stub
		repository.delete(classRoom);
	}

	@Override
	public ClassRoom getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

	@Override
	public List<ClassRoom> getByIdType(int idType) {
		// TODO Auto-generated method stub
		return repository.findByidClassType(idType);
	}

}
