package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PermanenceAdministrativeDao;
import com.example.stock.bean.Employe;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.PermanenceAdministrativeService;

@Service
public class PermanenceAdministrativeServiceImpl implements PermanenceAdministrativeService {
@Autowired
private PermanenceAdministrativeDao permanenceAdministrativeDao;
@Autowired
private EmployeService employeService;


@Override
public int save(PermanenceAdministrative permanenceAdministrative) {
	Employe employe = employeService.findByDoti(permanenceAdministrative.getEmploye().getDoti());
	if(employe == null) {
		return -2;
//	}else 	if(findByid(permanenceAdministrative.getId())!= null) {
//return -1;
}else {
	permanenceAdministrative.setEmploye(employe);
	permanenceAdministrativeDao.save(permanenceAdministrative);
		return 1;
}
	}

@Override
public PermanenceAdministrative findByid(Long id) {
	if (permanenceAdministrativeDao.findById(id).isPresent()) {
		return permanenceAdministrativeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	permanenceAdministrativeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}



@Override
public List<PermanenceAdministrative> findAll() {
	return permanenceAdministrativeDao.findAll();
}

@Override
public List<PermanenceAdministrative> findByEmployeId(Long id) {
	return permanenceAdministrativeDao.findByEmployeId(id);
}

@Override
public List<PermanenceAdministrative> findByEmployeEmail(String email) {
	return permanenceAdministrativeDao.findByEmployeEmail(email);
}

@Override
public List<PermanenceAdministrative> findByemployeDoti(Integer doti) {
	return permanenceAdministrativeDao.findByemployeDoti(doti);
}

@Override
public List<PermanenceAdministrative> findByPeriode(Integer periode) {
	return permanenceAdministrativeDao.findByPeriode(periode);
}

@Override
public PermanenceAdministrative findByDate(Date date) {
	return permanenceAdministrativeDao.findByDate(date);
}


}
