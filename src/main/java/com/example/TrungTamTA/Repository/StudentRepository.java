package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public Student findByid(int id);
}
