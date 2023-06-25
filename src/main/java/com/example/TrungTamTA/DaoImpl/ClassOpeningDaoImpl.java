package com.example.TrungTamTA.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.ClassOpeningDao;
import com.example.TrungTamTA.Entity.ClassOpening;
import com.example.TrungTamTA.Repository.ClassOpeningRepository;

@Repository
public class ClassOpeningDaoImpl implements ClassOpeningDao{

	@Autowired ClassOpeningRepository repository;
	
	@Override
	public List<ClassOpening> getAll() {
		return repository.findAll();
	}

	@Override
	public List<ClassOpening> getByidTeacher(int idTeacher) {
		return repository.findByidTeacher(idTeacher);
	}

	@Override
	public List<ClassOpening> getByidCourse(int idCourse) {
		return repository.findByidCourse(idCourse);
	}

	@Override
	public List<ClassOpening> getByidClassRoom(int idRoom) {
		return repository.findByidClassRoom(idRoom);
	}

	@Override
	public List<ClassOpening> getByidTutor(int idTutor) {
		return repository.findByidTutor(idTutor);
	}

	@Override
	public ClassOpening add(ClassOpening classOpening) {
		return repository.save(classOpening);
	}

	@Override
	public void update(ClassOpening classOpening) {
		repository.save(classOpening);
	}

	@Override
	public void delete(ClassOpening classOpening) {
		repository.delete(classOpening);
	}

	@Override
	public ClassOpening getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public List<ClassOpening> getDuplicateClassList(int idShift, int idClassOpening) {
		return repository.duplicateClassList(idShift, idClassOpening);
	}

	@Override
	public List<ClassOpening> getActivateClasses() {
		return repository.getActivateClasses();
	}

	@Override
	public List<ClassOpening> getBystatus(int status) {
		return repository.findBystatus(status);
	}
	
}
