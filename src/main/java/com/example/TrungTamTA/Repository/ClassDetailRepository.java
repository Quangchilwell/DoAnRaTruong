package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.ClassDetail;

public interface ClassDetailRepository extends JpaRepository<ClassDetail, Integer>{
	public ClassDetail findByid(int id);

	public List<ClassDetail> findByidClassOpening(int idClassOpening);
	
	public List<ClassDetail> findByidDayOfWeek(int idDayOfWeek);
	
	
	@Query(value = "SELECT cd.*\r\n"
			+ "FROM class_detail AS cd, teacher AS t, class_opening AS co\r\n"
			+ "WHERE cd.id_class_opening = co.id AND t.id = co.id_teacher \r\n"
			+ "	AND t.id = ?1 AND co.status = 0", nativeQuery = true)
	public List<ClassDetail> getByidTeacherAndStatus0(int teacherId);
	
}
