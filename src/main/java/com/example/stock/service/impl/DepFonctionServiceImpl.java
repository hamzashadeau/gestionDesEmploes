package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DepFonctionDao;
import com.example.stock.Dao.DepartementDao;
import com.example.stock.bean.DepFonction;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Fonction;
import com.example.stock.service.facade.DepFonctionService;
import com.example.stock.service.facade.DepartementService;
import com.example.stock.service.facade.FonctionService;

@Service
public class DepFonctionServiceImpl implements DepFonctionService {
@Autowired
private DepFonctionDao depFonctionDao;
@Autowired
private DepartementService departementService;
@Autowired
private FonctionService fonctionService;

@Override
public int save(DepFonction depFonction) {
	if(depFonction.getId() != null) {
return -1;
}else {
	Departement departement = departementService.findByNom(depFonction.getDepartemant().getNom());
	Fonction fonction = fonctionService.findByLibelle(depFonction.getFonction().getLibelle());
	depFonction.setDepartemant(departement);
	if(fonction == null) {
		fonctionService.save(depFonction.getFonction());
	}
	fonctionService.save(depFonction.getFonction());
	Fonction fonction1 = fonctionService.findByLibelle(depFonction.getFonction().getLibelle());
	depFonction.setFonction(fonction1);
	depFonctionDao.save(depFonction);
		return 1;
}
	}

@Override
public DepFonction findByid(Long id) {
	if (depFonctionDao.findById(id).isPresent()) {
		return depFonctionDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	depFonctionDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<DepFonction> findByDepartemantNom(String nomDepartemant) {
	return depFonctionDao.findByDepartemantNom(nomDepartemant);
}

@Override
public List<DepFonction> findByFonctionLibelle(String libelle) {
	return depFonctionDao.findByFonctionLibelle(libelle);
}

@Override
public List<DepFonction> findAll() {
	return depFonctionDao.findAll();
}

}
