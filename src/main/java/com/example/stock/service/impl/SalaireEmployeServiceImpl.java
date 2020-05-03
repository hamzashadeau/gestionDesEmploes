package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.SalaireEmployeDao;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.service.facade.SalaireEmployeService;

@Service
public class SalaireEmployeServiceImpl implements SalaireEmployeService {
@Autowired
private SalaireEmployeDao salaireEmployeDao;


@Override
public int save(SalaireEmploye salaireEmploye) {
	if(findByid(salaireEmploye.getId())!= null) {
return -1;
}else {
	salaireEmployeDao.save(salaireEmploye);
		return 1;
}
	}

@Override
public SalaireEmploye findByid(Long id) {
	if (salaireEmployeDao.findById(id).isPresent()) {
		return salaireEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	salaireEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<SalaireEmploye> findAll() {
	return salaireEmployeDao.findAll();
}

@Override
public List<SalaireEmploye> findByEmployeId(Long id) {
	return salaireEmployeDao.findByEmployeId(id);
}

@Override
public List<SalaireEmploye> findByEmployeEmail(String email) {
	return salaireEmployeDao.findByEmployeEmail(email);
}


@Override
public List<SalaireEmploye> findByMonatntModifie(Double montantModifier) {
	return salaireEmployeDao.findByMonatntModifie(montantModifier);
}

@Override
public List<SalaireEmploye> findByEmployeDoti(Integer doti) {
	return salaireEmployeDao.findByEmployeDoti(doti);
}

@Override
public List<SalaireEmploye> findBySalaireNet(Double montantDeBase) {
	return salaireEmployeDao.findBySalaireNet(montantDeBase);
}

}
