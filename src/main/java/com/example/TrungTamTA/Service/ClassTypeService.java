package com.example.TrungTamTA.Service;

import java.util.List;

import com.example.TrungTamTA.Model.ClassTypeDTO;

public interface ClassTypeService {
	public List<ClassTypeDTO> getAll();

	public void add(ClassTypeDTO classTypeDTO);

	public void update(ClassTypeDTO classTypeDTO);

	public void delete(int id);

	public ClassTypeDTO getByID(int id);
}
