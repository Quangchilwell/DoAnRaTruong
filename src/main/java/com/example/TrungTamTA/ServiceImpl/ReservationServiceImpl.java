package com.example.TrungTamTA.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Entity.Reservation;
import com.example.TrungTamTA.Model.ReservationDTO;
import com.example.TrungTamTA.Repository.ReservationRepository;
import com.example.TrungTamTA.Service.ClassOpeningService;
import com.example.TrungTamTA.Service.ReservationService;
import com.example.TrungTamTA.Service.StudentService;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired 
	private ReservationRepository reservationRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired ClassOpeningService classOpeningService;
	
	
	private void getInfo(ReservationDTO dto, Reservation reservation) {
		dto.setId(reservation.getId());
		dto.setIdClass(reservation.getIdClass());
		dto.setIdStudent(reservation.getIdStudent());
		dto.setReservationDate(String.valueOf(reservation.getReservationDate()));
		dto.setEndDate(String.valueOf(reservation.getEndDate()));
		dto.setReason(reservation.getReason());
		dto.setNote(reservation.getNote());
		dto.setStatus(reservation.getStatus());
		dto.setCreatedAt(reservation.getCreatedAt());
		dto.setUpdatedDate(reservation.getUpdatedDate());
		
		dto.setStudentDTO(studentService.getByID(reservation.getIdStudent()));
		dto.setClassOpeningDTO(classOpeningService.getByID(reservation.getIdClass()));
	}
	
	private void setInfo(ReservationDTO dto, Reservation reservation) {
		reservation.setIdClass(dto.getIdClass());
		reservation.setIdStudent(dto.getIdStudent());
		reservation.setReason(dto.getReason());
		reservation.setNote(dto.getNote());
		reservation.setStatus(dto.getStatus());
		reservation.setReservationDate(Date.valueOf(LocalDate.now()));
		if(dto.getEndDate() != null) {
			reservation.setEndDate(Date.valueOf(dto.getEndDate()));
		}
	}
	
	@Override
	public List<ReservationDTO> getAll() {
		List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
		List<Reservation> reservations = reservationRepository.findAll();
		
		for(Reservation reservation: reservations) {
			ReservationDTO dto = new ReservationDTO();
			getInfo(dto, reservation);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	@Override
	public ReservationDTO getById(int id) {
		Reservation reservation = reservationRepository.findByid(id);
		if(reservation != null) {
			ReservationDTO dto = new ReservationDTO();
			getInfo(dto, reservation);
			return dto;
		}
		return null;
	}

	@Override
	public List<ReservationDTO> getAllByIdStudent(int idStudent) {
		List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
		List<Reservation> reservations = reservationRepository.findByidStudent(idStudent);
		
		for(Reservation reservation: reservations) {
			ReservationDTO dto = new ReservationDTO();
			getInfo(dto, reservation);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<ReservationDTO> getAllByIdClass(int idClass) {
		List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
		List<Reservation> reservations = reservationRepository.findByidClass(idClass);
		
		for(Reservation reservation: reservations) {
			ReservationDTO dto = new ReservationDTO();
			getInfo(dto, reservation);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void add(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation();
		setInfo(reservationDTO, reservation);
		reservationRepository.save(reservation);
	}

	@Override
	public void update(ReservationDTO reservationDTO) {
		Reservation reservation = reservationRepository.findByid(reservationDTO.getId());
		if(reservation != null) {
			setInfo(reservationDTO, reservation);
			reservationRepository.save(reservation);
		}
	}

	@Override
	public void delete(int id) {
		Reservation reservation = reservationRepository.findByid(id);
		if(reservation != null) {
			reservationRepository.delete(reservation);
		}
	}

}
