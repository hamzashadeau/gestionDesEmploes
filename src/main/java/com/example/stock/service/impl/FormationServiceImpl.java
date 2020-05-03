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
public List<Formation> findByemployéId(Long id) {
	return formationDao.findByEmployéId(id);
}

@Override
public List<Formation> findByemployéEmail(String email) {
	return formationDao.findByEmployéEmail(email);
}

@Override
public List<Formation> findByemployéDoti(Integer doti) {
	return formationDao.findByEmployéDoti(doti);
}
@Override
public List<Formation> findFormationDeEmploye(Employe employe) {
	List<Formation> punitionEmployes = findByemployéDoti(employe.getDoti());
	List<Formation> resultat = new ArrayList<Formation>();
	for (Formation punitionEmploye : punitionEmployes) {
		if(DateUlils.VerifieDate(punitionEmploye.getAnnee()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}
}
