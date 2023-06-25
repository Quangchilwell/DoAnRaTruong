package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.StudentDetailInCompletedClass;

public interface StudentDetailInCompletedClassRepository extends JpaRepository<StudentDetailInCompletedClass, Integer>{
	public StudentDetailInCompletedClass findByid(int id);
	
	public List<StudentDetailInCompletedClass> findByidStudent(int idStudent);
	
	public List<StudentDetailInCompletedClass> findByidClass(int idClass);
}
