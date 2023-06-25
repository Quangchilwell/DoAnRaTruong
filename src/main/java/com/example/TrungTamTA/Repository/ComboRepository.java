package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Combo;

public interface ComboRepository extends JpaRepository<Combo, Integer>{
	public Combo findByid(int id);
}
