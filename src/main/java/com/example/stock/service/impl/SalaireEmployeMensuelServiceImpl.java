package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.SalaireEmployeMensuelDao;
import com.example.stock.bean.SalaireEmployeMensuel;
import com.example.stock.service.facade.SalaireEmployeMensuelService;

@Service
public class SalaireEmployeMensuelServiceImpl implements SalaireEmployeMensuelService {
@Autowired
private SalaireEmployeMensuelDao salaireEmployeMensuelDao;


@Override
public int save(SalaireEmployeMensuel salaireEmployeMensuel) {
	if(findByid(salaireEmployeMensuel.getId())!= null) {
return -1;
}else {
	salaireEmployeMensuelDao.save(salaireEmployeMensuel);
		return 1;
}
	}

@Override
public SalaireEmployeMensuel findByid(Long id) {
	if (salaireEmployeMensuelDao.findById(id).isPresent()) {
		return salaireEmployeMensuelDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	salaireEmployeMensuelDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<SalaireEmployeMensuel> findAll() {
	return salaireEmployeMensuelDao.findAll();
}

@Override
public List<SalaireEmployeMensuel> findByEmployeId(Long id) {
	return salaireEmployeMensuelDao.findByEmployeId(id);
}

@Override
public List<SalaireEmployeMensuel> findByEmployeEmail(String email) {
	return salaireEmployeMensuelDao.findByEmployeEmail(email);
}


@Override
public List<SalaireEmployeMensuel> findByMonatntModifie(Double montantModifier) {
	return salaireEmployeMensuelDao.findByMonatntModifie(montantModifier);
}

@Override
public List<SalaireEmployeMensuel> findByEmployeDoti(Integer doti) {
	return salaireEmployeMensuelDao.findByEmployeDoti(doti);
}

@Override
public List<SalaireEmployeMensuel> findByEmployeDotiAndYearAndMois(Integer doti, Integer year, Integer mois) {
	return salaireEmployeMensuelDao.findByEmployeDotiAndYearAndMois(doti, year, mois);
}



}
