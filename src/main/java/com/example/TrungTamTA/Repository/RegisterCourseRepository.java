package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.RegisterCourse;

public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, Integer>{
	public RegisterCourse findByid(int id);
	
	public List<RegisterCourse> findByidCourse(int idCourse);
	
	public List<RegisterCourse> findByidStudent(int idStudent);
	
	public List<RegisterCourse> findByidClassOpening(int idClass);
	
	public List<RegisterCourse> findByidClassOpeningAndSoftDelete(int idClass, int softDelete);
	
	@Query(value = "SELECT rc FROM RegisterCourse rc WHERE rc.status = 0")
	public List<RegisterCourse> getRegistersHaveNotClass();
	
	@Query(value = "SELECT rc FROM RegisterCourse rc WHERE rc.status != 0 AND rc.idClassOpening = ?1")
	public List<RegisterCourse> getStudentsInClass(int idClass);
	
	@Query(value = "SELECT rc.*\r\n"
			+ "FROM register_course AS rc\r\n"
			+ "WHERE rc.id_course = ?1 AND rc.enable=\"Yes\"", nativeQuery = true)
	public List<RegisterCourse> getRegistersCanOpenClass(int idCourse);
	
	@Query(value = "SELECT * FROM register_course as rc \r\n"
			+ "WHERE rc.id_class_opening = ?1 AND rc.id_student NOT IN (\r\n"
			+ "	SELECT da.id_student FROM draft_attendance as da \r\n"
			+ ")", nativeQuery = true)
	public List<RegisterCourse> getStudentsToCheckIn(int idCLass);
}
