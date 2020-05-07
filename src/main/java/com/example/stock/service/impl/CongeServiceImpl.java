package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.CongeDao;
import com.example.stock.bean.Congé;
import com.example.stock.bean.Employe;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.CongeService;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.TypeCongeeService;

@Service
public class CongeServiceImpl implements CongeService {
@Autowired
private CongeDao congeDao;
@Autowired
private EmployeService employeService;
@Autowired
private TypeCongeeService typeCongeeService;


@Override
public int save(Congé congé) {
	Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
	TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
	if(employe == null) {
		return -2;
	}else if(congé2 == null) {
		return -3;
	}else 
	if(findByid(congé.getId())!= null) {
return -1;
}else {
	congé.setCongee(congé2);
	congé.setEmploye(employe);
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
