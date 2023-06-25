package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer>{
	public ClassRoom findByid(int id);
	
	public List<ClassRoom> findByidClassType(int idType);
}
