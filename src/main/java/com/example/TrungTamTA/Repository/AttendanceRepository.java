package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TrungTamTA.Entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	public Attendance findByid(int id);
	
	public List<Attendance> findByidStudent(int idStudent);
	
	public List<Attendance> findByidClass(int idCLass);
	
	public List<Attendance> findByidStudentAndIdClass(int idStudent, int idClass);
	
	public List<Attendance> findByidLesson(int idLesson);
	
	public List<Attendance> findBystatus(int status); // 0: Vắng, 1: Đi học.
	
	public List<Attendance> findByidStudentAndStatus(int studentId, int status);
	
}
