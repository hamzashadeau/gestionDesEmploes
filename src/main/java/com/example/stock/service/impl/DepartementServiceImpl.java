package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DepartementDao;
import com.example.stock.bean.Departement;
import com.example.stock.service.facade.DepartementService;

@Service
public class DepartementServiceImpl implements DepartementService {
@Autowired
private DepartementDao departementDao;


@Override
public int save(Departement departement) {
	if(findByid(departement.getId())!= null) {
return -1;
}else {
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
public Departement findByChefEmail(String email) {
	return departementDao.findByChefEmail(email);
}

@Override
public Departement findByChefDoti(Integer doti) {
	return departementDao.findByChefDoti(doti);
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
