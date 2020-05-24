package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PunitionDao;
import com.example.stock.bean.Punition;
import com.example.stock.service.facade.PunitionService;

@Service
public class PunitionServiceImpl implements PunitionService {
@Autowired
private PunitionDao punitionDao;


@Override
public int save(Punition punition) {
	if(findByid(punition.getId())!= null) {
return -1;
}else {
	punitionDao.save(punition);
		return 1;
}
	}

@Override
public Punition findByid(Long id) {
	if (punitionDao.findById(id).isPresent()) {
		return punitionDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	punitionDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<Punition> findAll() {
	return punitionDao.findAll();
}

@Override
public Punition findByLibelle(String libelle) {
	return punitionDao.findByLibelle(libelle);
}

@Override
public List<Punition> findByType(String type) {
	return punitionDao.findByType(type);
}

}
