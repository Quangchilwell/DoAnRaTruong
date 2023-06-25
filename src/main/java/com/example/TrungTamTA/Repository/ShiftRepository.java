package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Integer>{
	public Shift findByid(int id);
}
