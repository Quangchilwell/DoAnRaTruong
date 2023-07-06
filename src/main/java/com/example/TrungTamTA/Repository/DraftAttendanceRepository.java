package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.DraftAttendance;

public interface DraftAttendanceRepository extends JpaRepository<DraftAttendance, Integer>{
	public DraftAttendance findByidStudent(int idStudent);
}
