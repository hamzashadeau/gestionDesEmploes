package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PrixDao;
import com.example.stock.bean.Prix;
import com.example.stock.service.facade.PrixService;

@Service
public class PrixServiceImpl implements PrixService {
@Autowired
private PrixDao prixDao;


@Override
public int save(Prix prix) {
	if(findByid(prix.getId())!= null) {
return -1;
}else {
	prixDao.save(prix);
		return 1;
}
	}

@Override
public Prix findByid(Long id) {
	if (prixDao.findById(id).isPresent()) {
		return prixDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	prixDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<Prix> findAll() {
	return prixDao.findAll();
}

@Override
public Prix findByLibelle(String libelle) {
	return prixDao.findByLibelle(libelle);
}

}
