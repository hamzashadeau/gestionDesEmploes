package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.FormationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.FormationService;

@Service
public class FormationServiceImpl implements FormationService {
@Autowired
private FormationDao formationDao;


@Override
public int save(Formation formation) {
	if(findByid(formation.getId())!= null) {
return -1;
}else {
	formationDao.save(formation);
		return 1;
}
	}

@Override
public Formation findByid(Long id) {
	if (formationDao.findById(id).isPresent()) {
		return formationDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	formationDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<Formation> findAll() {
	return formationDao.findAll();
}

@Override
public List<Formation> findByemployeId(Long id) {
	return formationDao.findByEmployeId(id);
}

@Override
public List<Formation> findByemployeEmail(String email) {
	return formationDao.findByEmployeEmail(email);
}

@Override
public List<Formation> findByemployeDoti(Integer doti) {
	return formationDao.findByEmployeDoti(doti);
}
@Override
public List<Formation> findFormationDeEmploye(Employe employe) {
	List<Formation> punitionEmployes = findByemployeDoti(employe.getDoti());
	List<Formation> resultat = new ArrayList<Formation>();
	for (Formation punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getAnnee()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}
}
