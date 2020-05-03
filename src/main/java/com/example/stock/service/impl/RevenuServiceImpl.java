package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.RevenuDao;
import com.example.stock.bean.Revenu;
import com.example.stock.service.facade.RevenuService;

@Service
public class RevenuServiceImpl implements RevenuService {
@Autowired
private RevenuDao revenuDao;


@Override
public int save(Revenu revenu) {
	if(findByid(revenu.getId())!= null) {
return -1;
}else {
	revenuDao.save(revenu);
		return 1;
}
	}

@Override
public Revenu findByid(Long id) {
	if (revenuDao.findById(id).isPresent()) {
		return revenuDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	revenuDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<Revenu> findAll() {
	return revenuDao.findAll();
}

@Override
public List<Revenu> findByLibelle(String libelle) {
	return revenuDao.findByLibelle(libelle);
}

@Override
public List<Revenu> findByMontant(Double montant) {
	return revenuDao.findByMontant(montant);
}

}
