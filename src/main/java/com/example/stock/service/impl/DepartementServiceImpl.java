package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DepartementDao;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Employe;
import com.example.stock.service.facade.DepartementService;
import com.example.stock.service.facade.EmployeService;

@Service
public class DepartementServiceImpl implements DepartementService {
@Autowired
private DepartementDao departementDao;
@Autowired
private EmployeService employeService;

@Override
public int save(Departement departement) {
	Employe employe = employeService.findByDoti(departement.getChefdoti());
	if(employe == null) {
		return -2;
	} else if (departement.getId() != null) {
		return -3;
	} else {
	departement.setChefdoti(employe.getDoti());
	departementDao.save(departement);
		return 1;
	}
}

@Override
public Departement findByid(Long id) {
	if (departementDao.findById(id).isPresent()) {
		return departementDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	departementDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public Departement findByNom(String nom){
	return departementDao.findByNom(nom);
}


@Override
public Departement findByChefdoti(Integer doti) {
	return departementDao.findByChefdoti(doti);
}

@Override
public List<Departement> findAll() {
	return departementDao.findAll();
}

@Override
public int nombreDesDepartements() {
	return findAll().size();
}

}
