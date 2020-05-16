package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PunitionEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.PunitionEmployeService;

@Service
public class PunitionEmployeServiceImpl implements PunitionEmployeService {
@Autowired
private PunitionEmployeDao punitionEmployeDao;


@Override
public int save(PunitionEmploye punitionEmploye) {
	if(findByid(punitionEmploye.getId())!= null) {
return -1;
}else {
	punitionEmployeDao.save(punitionEmploye);
		return 1;
}
	}

@Override
public PunitionEmploye findByid(Long id) {
	if (punitionEmployeDao.findById(id).isPresent()) {
		return punitionEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	punitionEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<PunitionEmploye> findAll() {
	return punitionEmployeDao.findAll();
}

@Override
public List<PunitionEmploye> findByEmployeEmail(String email) {
	return punitionEmployeDao.findByEmployeEmail(email);
}


@Override
public List<PunitionEmploye> findByPunitionLibelle(String libelle) {
	return punitionEmployeDao.findByPunitionLibelle(libelle);
}

@Override
public List<PunitionEmploye> findByPunitionType(String type) {
	return punitionEmployeDao.findByPunitionType(type);
}

@Override
public List<PunitionEmploye> findByEmployeDoti(Integer doti) {
	return punitionEmployeDao.findByEmployeDoti(doti);
}

@Override
public List<PunitionEmploye> findByEmployeId(Long id) {
	return punitionEmployeDao.findByEmployeId(id);
}
@Override
public List<PunitionEmploye> findPunitionDeEmploye(Employe employe) {
	List<PunitionEmploye> punitionEmployes = findByEmployeDoti(employe.getDoti());
	List<PunitionEmploye> resultat = new ArrayList<PunitionEmploye>();
	for (PunitionEmploye punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getDateObtenation()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

}
