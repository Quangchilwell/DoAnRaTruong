package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Integer>{
	public Tutor findByid(int id);
}
