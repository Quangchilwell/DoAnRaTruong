package com.example.TrungTamTA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Entity.ClassOpening;

public interface ClassOpeningRepository extends JpaRepository<ClassOpening, Integer>{
	public ClassOpening findByid(int id);
	
	public List<ClassOpening> findBystatus(int status);
	
	public List<ClassOpening> findByidTeacher(int idTeacher);
	
	public List<ClassOpening> findByidClassRoom(int idClassRoom);
	
	public List<ClassOpening> findByidCourse(int idCourse);
	
	public List<ClassOpening> findByidTutor(int idTutor); 
	
	// Danh sach giao vien dap ung
	@Query(value = "SELECT co \r\n"
			+ "FROM ClassOpening co, ClassDetail cd\r\n"
			+ "WHERE co.id = cd.idClassOpening AND co.idShift = ?1\r\n"
			+ "AND co.id != ?2 \r\n"
			+ "AND cd.idDayOfWeek IN (\r\n"
			+ "	SELECT cd.idDayOfWeek \r\n"
			+ "    FROM ClassDetail cd\r\n"
			+ "    WHERE cd.idClassOpening = ?2 \r\n"
			+ ")\r\n"
			+ "GROUP BY co.id")
	public List<ClassOpening> duplicateClassList(int idShift, int idClassOpening);
	
	@Query(value = "SELECT co FROM ClassOpening co WHERE co.status = 0")
	public List<ClassOpening> getActivateClasses();
}
