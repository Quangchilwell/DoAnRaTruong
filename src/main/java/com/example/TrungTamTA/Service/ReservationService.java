package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ReservationDTO;

public interface ReservationService {
	
	List<ReservationDTO> getAll();
	
	ReservationDTO getById(int id);
	
	List<ReservationDTO> getAllByIdStudent(int idStudent);
	
	List<ReservationDTO> getAllByIdClass(int idClass);
	
	void add(ReservationDTO reservationDTO);
	
	void update(ReservationDTO reservationDTO);
	
	void delete(int id);
}
