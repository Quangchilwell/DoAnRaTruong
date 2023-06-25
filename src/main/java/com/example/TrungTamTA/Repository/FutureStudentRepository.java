package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.FutureStudent;

public interface FutureStudentRepository extends JpaRepository<FutureStudent, Integer>{
	public FutureStudent findByid(int id);
	
	public List<FutureStudent> findBystatus(int status);
}
