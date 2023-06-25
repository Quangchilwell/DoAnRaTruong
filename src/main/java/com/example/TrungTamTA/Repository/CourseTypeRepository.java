package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.CourseType;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer>{
	public CourseType findByid(int id);
}
