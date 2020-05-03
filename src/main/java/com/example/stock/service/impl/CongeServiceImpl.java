package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.CongeDao;
import com.example.stock.bean.Congé;
import com.example.stock.service.facade.CongeService;

@Service
public class CongeServiceImpl implements CongeService {
@Autowired
private CongeDao congeDao;


@Override
public int save(Congé congé) {
	if(findByid(congé.getId())!= null) {
return -1;
}else {
	congeDao.save(congé);
		return 1;
}
	}

@Override
public Congé findByid(Long id) {
	if (congeDao.findById(id).isPresent()) {
		return congeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	congeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<Congé> findAll() {
	return congeDao.findAll();
}

@Override
public List<Congé> findByCongeeLibelle(String libelle) {
	return congeDao.findByCongeeLibelle(libelle);
}

@Override
public List<Congé> findByEmployeEmail(String email) {
	return congeDao.findByEmployeEmail(email);
}

@Override
public List<Congé> findByEmployeDoti(Integer doti) {
	return congeDao.findByEmployeDoti(doti);
}

@Override
public List<Congé> findByEtat(String etat) {
	return congeDao.findByEtat(etat);
}



}
