package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.FutureStudentDTO;

public interface FutureStudentService {
	public List<FutureStudentDTO> getAll();
	
	public List<FutureStudentDTO> getBystatus(int status);
	
	public void add(FutureStudentDTO futureStudentDTO);
	
	public void update(FutureStudentDTO futureStudentDTO);
	
	public void delete(int id);
	
	public FutureStudentDTO getByID(int id);
}
