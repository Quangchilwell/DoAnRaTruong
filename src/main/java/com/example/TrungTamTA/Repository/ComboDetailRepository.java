package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.ComboDetail;

public interface ComboDetailRepository extends JpaRepository<ComboDetail, Integer>{
	public ComboDetail findByid(int id);
	
	public List<ComboDetail> findByidCourse(int idCourse);
	
	
	@Query(value = "SELECT cbd.*\r\n"
			+ "FROM combo_detail AS cbd, course AS c\r\n"
			+ "WHERE cbd.id_course=c.id AND cbd.id_combo=?1\r\n"
			+ "ORDER BY c.rank ASC", nativeQuery = true)
	public List<ComboDetail> getByidCombo(int idCombo);
}
