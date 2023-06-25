package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	public Course findByid(int id);
	
	
	public List<Course> findByidCourseType(int idType);
	
	public List<Course> findByidTeachingForm(int idForm);
	
	@Query(value = "SELECT c \r\n"
			+ "FROM Course c \r\n"
			+ "WHERE c.id NOT IN (\r\n"
			+ "    SELECT cd.idCourse \r\n"
			+ "	FROM ComboDetail cd \r\n"
			+ "	WHERE cd.idCombo = ?1 \r\n"
			+ ")")
	public List<Course> getCoursesNotInCombo(int idCombo);
	
	@Query(value = "SELECT c FROM Course c \r\n"
					+ "WHERE c.idTeachingForm = 1")
	public List<Course> getOfflineCourses();
	
	@Query(value = "SELECT c FROM Course c \r\n"
			+ "WHERE c.idTeachingForm = 2")
	public List<Course> getOnlineCourses();
}
