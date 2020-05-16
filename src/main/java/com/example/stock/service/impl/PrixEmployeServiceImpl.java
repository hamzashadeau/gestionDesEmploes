package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PrixEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.service.facade.PrixEmployeService;

@Service
public class PrixEmployeServiceImpl implements PrixEmployeService {
	
@Autowired
private PrixEmployeDao prixEmployeDao;


@Override
public int save(PrixEmploye prixEmploye) {
	if(findByid(prixEmploye.getId())!= null) {
return -1;
}else {
	prixEmployeDao.save(prixEmploye);
		return 1;
}
	}

@Override
public PrixEmploye findByid(Long id) {
	if (prixEmployeDao.findById(id).isPresent()) {
		return prixEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	prixEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<PrixEmploye> findAll() {
	return prixEmployeDao.findAll();
}

@Override
public List<PrixEmploye> findByEmployeEmail(String email) {
	return prixEmployeDao.findByEmployeEmail(email);
}

@Override
public List<PrixEmploye> findByPrixLibelle(String libelle) {
	return prixEmployeDao.findByPrixLibelle(libelle);
}
@Override
public List<PrixEmploye> findPrixDeEmploye(Employe employe) {
	List<PrixEmploye> punitionEmployes = findByEmployeEmail(employe.getEmail());
	List<PrixEmploye> resultat = new ArrayList<PrixEmploye>();
	for (PrixEmploye punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getDateDeObtenation()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

}
