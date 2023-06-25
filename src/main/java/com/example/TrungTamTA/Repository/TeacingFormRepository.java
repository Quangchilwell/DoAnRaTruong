package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.TeachingForm;

public interface TeacingFormRepository extends JpaRepository<TeachingForm, Integer>{
	public TeachingForm findByid(int id);
}
