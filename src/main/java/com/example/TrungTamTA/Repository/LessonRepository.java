package com.example.TrungTamTA.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TrungTamTA.Constant.LessonStatus;
import com.example.TrungTamTA.Entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
	public Lesson findByid(int id);
	
	public List<Lesson> findBystatusAndIdClassOpening(int status, int idClass);
	
	public Lesson findByidClassOpeningAndDay(int idClass, Date date);
	
	public List<Lesson> findByidClassOpening(int idClass);
	
	@Query(value = "SELECT les FROM Lesson les\r\n"
			+ "WHERE les.idClassOpening = ?1 AND les.day = ?2 AND les.status=" + LessonStatus.DA_HOAN_THANH)
	public Lesson checkClassCompleted(int idClass, Date date, int status);
	
	@Query(value = "SELECT les FROM Lesson les\r\n"
			+ "WHERE les.idClassOpening = ?1 AND les.status = 1")
	public List<Lesson> getLessonsWereCompleted(int idClass);
	
	@Query(value = "SELECT les FROM Lesson les\r\n"
			+ "WHERE les.idClassOpening = ?1 AND les.status = -1")
	public List<Lesson> getLessonssWerePostPone(int idClass);
}
