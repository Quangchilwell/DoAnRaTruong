package com.example.TrungTamTA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.DayOfWeek;

public interface DayOfWeekReporitory extends JpaRepository<DayOfWeek, Integer>{
	public DayOfWeek findByid(int id);
}
