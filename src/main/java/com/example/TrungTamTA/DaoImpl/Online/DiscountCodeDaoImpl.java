package com.example.TrungTamTA.DaoImpl.Online;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.TrungTamTA.Dao.Online.DiscountCodeDao;
import com.example.TrungTamTA.Entity.Online.DiscountCode;
import com.example.TrungTamTA.Repository.Online.DiscountCodeRepository;

@Repository
public class DiscountCodeDaoImpl implements DiscountCodeDao{

	@Autowired DiscountCodeRepository repository;
	
	@Override
	public List<DiscountCode> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(DiscountCode discountCode) {
		repository.save(discountCode);
	}

	@Override
	public void update(DiscountCode discountCode) {
		repository.save(discountCode);
	}

	@Override
	public void delete(DiscountCode discountCode) {
		repository.delete(discountCode);
	}

	@Override
	public DiscountCode getByID(int id) {
		return repository.findByid(id);
	}

}
