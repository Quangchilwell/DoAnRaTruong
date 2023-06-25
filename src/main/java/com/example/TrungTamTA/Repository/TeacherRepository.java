package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	public Teacher findByid(int id);
	
	@Query(value = "SELECT t FROM Teacher t\r\n"
			+ "WHERE t.id NOT IN(\r\n"
			+ "    SELECT oc.idTeacher FROM OnlineCourse oc\r\n"
			+ "    WHERE oc.idCourse = ?1 \r\n"
			+ ")")
	public List<Teacher> getTeacherForOnlineCourse(int idCourse);
}
