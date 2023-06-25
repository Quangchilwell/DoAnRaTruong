package com.example.TrungTamTA.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TrungTamTA.Dao.ClassTypeDao;
import com.example.TrungTamTA.Entity.ClassType;
import com.example.TrungTamTA.Model.ClassTypeDTO;
import com.example.TrungTamTA.Service.ClassTypeService;

@Service
@Transactional
public class ClassTypeServiceImpl implements ClassTypeService{
	 @Autowired
	    ClassTypeDao classTypeDao;

	    private void setInfo(ClassTypeDTO classTypeDTO, ClassType classType)
	    {
	        classTypeDTO.setId(classType.getId());
	        classTypeDTO.setName(classType.getName());
	        classTypeDTO.setQuantityTables(classType.getQuantityTables());
	        classTypeDTO.setQuantityChairs(classType.getQuantityChairs());
	        classTypeDTO.setNote(classType.getNote());
	        classTypeDTO.setCreatedDate(classType.getCreatedDate());
	        classTypeDTO.setUpdatedDate(classType.getUpdatedDate());
	    }

	    @Override
	    public List<ClassTypeDTO> getAll() {
	        List<ClassTypeDTO> classTypeDTOS = new ArrayList<>();
	        List<ClassType> classTypes = classTypeDao.getAll();

	        for(ClassType classType: classTypes)
	        {
	            ClassTypeDTO classTypeDTO = new ClassTypeDTO();
	            setInfo(classTypeDTO, classType);
	            classTypeDTOS.add(classTypeDTO);
	        }

	        return classTypeDTOS;
	    }

	    @Override
	    public void add(ClassTypeDTO classTypeDTO) {
	        LocalDateTime createdDate = LocalDateTime.now();
	        ClassType classType = new ClassType();

	        classType.setName(classTypeDTO.getName());
	        classType.setQuantityTables(classTypeDTO.getQuantityTables());
	        classType.setQuantityChairs(classTypeDTO.getQuantityChairs());
	        classType.setNote(classTypeDTO.getNote());
	        classType.setCreatedDate(Timestamp.valueOf(createdDate));
	        classType.setUpdatedDate(null);

	        classTypeDao.add(classType);
	    }

	    @Override
	    public void update(ClassTypeDTO classTypeDTO) {
	        LocalDateTime updatedDate = LocalDateTime.now();
	        ClassType classType = classTypeDao.getByID(classTypeDTO.getId());
	        if(classType != null) {
	        	classType.setName(classTypeDTO.getName());
	        	classType.setQuantityTables(classTypeDTO.getQuantityTables());
	        	classType.setQuantityChairs(classTypeDTO.getQuantityChairs());
	        	classType.setNote(classTypeDTO.getNote());
//	        	classType.setCreatedDate(classTypeDTO.getCreatedDate());
	        	classType.setUpdatedDate(Timestamp.valueOf(updatedDate));
	        	classTypeDao.update(classType);	        	
	        }
	    }

	    @Override
	    public void delete(int id) {
	        ClassType classType = classTypeDao.getByID(id);
	        if(classType != null)
	        {
	            classTypeDao.delete(classType);
	        }
	    }

	    @Override
	    public ClassTypeDTO getByID(int id) {
	        ClassType classType = classTypeDao.getByID(id);
	        if(classType != null)
	        {
	            ClassTypeDTO classTypeDTO = new ClassTypeDTO();
	            setInfo(classTypeDTO, classType);
	            return  classTypeDTO;
	        }
	        return null;
	    }

}
