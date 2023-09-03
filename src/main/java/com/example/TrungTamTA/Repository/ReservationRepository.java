package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	public Reservation findByid(int id);
	
	public List<Reservation> findByidStudent(int idStudent);
	
	public List<Reservation> findByidClass(int idClass);
}
