package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ClassRoomDao;
import com.example.TrungTamTA.Dao.ClassTypeDao;
import com.example.TrungTamTA.Entity.ClassRoom;
import com.example.TrungTamTA.Model.ClassRoomDTO;
import com.example.TrungTamTA.Model.ClassTypeDTO;
import com.example.TrungTamTA.Service.ClassRoomService;
import com.example.TrungTamTA.Service.ClassTypeService;

@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService{

	@Autowired ClassRoomDao classRoomDao;
	
	@Autowired ClassTypeService classTypeService;
	
	private void getInfo(ClassRoom classRoom, ClassRoomDTO classRoomDTO) {
		ClassTypeDTO classTypeDTO = classTypeService.getByID(classRoom.getIdClassType());
		classRoomDTO.setId(classRoom.getId());
		classRoomDTO.setClassTypeDTO(classTypeDTO);
		classRoomDTO.setName(classRoom.getName());
		classRoomDTO.setStatus(classRoom.getStatus());
		classRoomDTO.setCreatedDate(classRoom.getCreatedDate());
		classRoomDTO.setUpdatedDate(classRoom.getUpdatedDate());
	}
	
	private void setInfo(ClassRoom classRoom, ClassRoomDTO classRoomDTO) {
		classRoom.setName(classRoomDTO.getName());
		classRoom.setIdClassType(classRoomDTO.getClassTypeDTO().getId());
	}
	
	@Override
	public List<ClassRoomDTO> getAll() {
		// TODO Auto-generated method stub
		List<ClassRoom> classRooms = classRoomDao.getAll();
		List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
		
		for(ClassRoom classRoom: classRooms)
		{
			ClassRoomDTO classRoomDTO = new ClassRoomDTO();
			getInfo(classRoom, classRoomDTO);
			classRoomDTOs.add(classRoomDTO);
		}
		return classRoomDTOs;
	}

	@Override
	public void add(ClassRoomDTO classRoomDTO) {
		LocalDateTime createdDate = LocalDateTime.now();
		ClassRoom classRoom = new ClassRoom();
		setInfo(classRoom, classRoomDTO);
		classRoom.setStatus(0);
		classRoom.setCreatedDate(Timestamp.valueOf(createdDate));
		classRoom.setUpdatedDate(null);
		classRoomDao.add(classRoom);
		
	}

	@Override
	public void update(ClassRoomDTO classRoomDTO) {
		LocalDateTime updatedDate = LocalDateTime.now();
		ClassRoom classRoom = classRoomDao.getByID(classRoomDTO.getId());
		if(classRoom != null) {
			setInfo(classRoom, classRoomDTO);
			classRoom.setStatus(classRoomDTO.getStatus());
			classRoom.setUpdatedDate(Timestamp.valueOf(updatedDate));
			classRoomDao.update(classRoom);
		}
	}

	@Override
	public void delete(int id) {
		ClassRoom classRoom = classRoomDao.getByID(id);
		if(classRoom != null)
		{
			classRoomDao.delete(classRoom);
		}
	}

	@Override
	public ClassRoomDTO getByID(int id) {
		ClassRoom classRoom = classRoomDao.getByID(id);
		if(classRoom != null)
		{
			ClassRoomDTO classRoomDTO = new ClassRoomDTO();
			getInfo(classRoom, classRoomDTO);
			return classRoomDTO;
		}
		return null;
	}

	@Override
	public List<ClassRoomDTO> getByIdType(int idType) {
		// TODO Auto-generated method stub
		List<ClassRoom> classRooms = classRoomDao.getByIdType(idType);
		List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
		
		for(ClassRoom classRoom: classRooms)
		{
			ClassRoomDTO classRoomDTO = new ClassRoomDTO();
			getInfo(classRoom, classRoomDTO);
			classRoomDTOs.add(classRoomDTO);
		}
		return classRoomDTOs;
	}

}
