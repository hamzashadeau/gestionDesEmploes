package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.TypeCongeeDao;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.TypeCongeeService;

@Service
public class TypeCongeeServiceImpl implements TypeCongeeService {
@Autowired
private TypeCongeeDao typeCongeeDao;


@Override
public int save(TypeCongee typeCongee) {
	if(findByid(typeCongee.getId())!= null) {
return -1;
}else {
	typeCongeeDao.save(typeCongee);
		return 1;
}
	}

@Override
public TypeCongee findByid(Long id) {
	if (typeCongeeDao.findById(id).isPresent()) {
		return typeCongeeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	typeCongeeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public TypeCongee findByLibelle(String type) {
	return typeCongeeDao.findByLibelle(type);
}

@Override
public List<TypeCongee> findAll() {
	return typeCongeeDao.findAll();
}




}
