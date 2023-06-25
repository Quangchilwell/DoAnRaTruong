package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.ClassType;

public interface ClassTypeRepository extends JpaRepository<ClassType, Integer>{
	public ClassType findByid(int id);
}
