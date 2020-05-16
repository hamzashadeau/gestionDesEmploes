package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.EmolumentsDao;
import com.example.stock.bean.Emoluments;
import com.example.stock.service.facade.EmolumentsService;

@Service
public class EmoulumentsServiceImpl implements EmolumentsService {
@Autowired
private EmolumentsDao emolumentsDao;


@Override
public int save(Emoluments emoluments) {
	if(emoluments.getId() != null) {
return -1;
}else {
	emolumentsDao.save(emoluments);
		return 1;
}
	}

@Override
public Emoluments findByid(Long id) {
	if (emolumentsDao.findById(id).isPresent()) {
		return emolumentsDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	emolumentsDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<Emoluments> findAll() {
	return emolumentsDao.findAll();
}

@Override
public Emoluments findByLibelle(String libelle) {
	return emolumentsDao.findByLibelle(libelle);
}

@Override
public List<Emoluments> findByMontant(Double montant) {
	return emolumentsDao.findByMontant(montant);
}

}
