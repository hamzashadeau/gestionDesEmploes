package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.PermanenceAdministrativeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.Utilis.HashUtil;
import com.example.stock.bean.Employe;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.PermanenceAdministrativeService;

@Service
public class PermanenceAdministrativeServiceImpl implements PermanenceAdministrativeService {
@Autowired
private PermanenceAdministrativeDao permanenceAdministrativeDao;
@Autowired
private EmployeService employeService;
@Autowired
private NotificationEmployeService notificationEmployeService;
@Autowired
private NotificationService notificationService;

@Override
public int save(PermanenceAdministrative permanenceAdministrative) {
	Employe employe = employeService.findByDoti(permanenceAdministrative.getEmploye().getDoti());
	if(employe == null) {
		return -2;
	}else 	if(permanenceAdministrative.getId() !=  null) {
return -1;
}else {
	TypeNotification typeNotification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "save permanence");
	notificationEmployeService.save(notificationEmploye);
	permanenceAdministrative.setEmploye(employe);
	permanenceAdministrativeDao.save(permanenceAdministrative);
		return 1;
}
	}
@Override
public int update(PermanenceAdministrative permanenceAdministrative) {
	Employe employe = employeService.findByDoti(permanenceAdministrative.getEmploye().getDoti());
	if(employe == null) {
		return -2;
	}else 	if(permanenceAdministrative.getId() ==  null) {
return -1;
}else {
	TypeNotification typeNotification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "update permanence");
	notificationEmployeService.save(notificationEmploye);
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
	PermanenceAdministrative permanenceAdministrative = findByid(id);
	Employe employe = permanenceAdministrative.getEmploye();
	TypeNotification typeNotification = notificationService.findByType("delete");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,employe, new Date(), "save permanence");
	notificationEmployeService.save(notificationEmploye);
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
public List<PermanenceAdministrative> findByemployeDoti(String doti) {
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
@Override
public List<PermanenceAdministrative> findByAnnee(Integer annee) {
List<PermanenceAdministrative> permanenceAdministratives = findAll();
List<PermanenceAdministrative> resultats = new ArrayList<PermanenceAdministrative>() ;
permanenceAdministratives.forEach(per ->{
	System.out.println(DateUlils.getYear(per.getDate()));
	if(DateUlils.getYear(per.getDate()).equals(annee)) {
		resultats.add(per);
	}
});
	return resultats;
}
@Override
public List<PermanenceAdministrative> findByAnneeAndemployeDoti(Integer annee, String doti) {
List<PermanenceAdministrative> permanenceAdministratives = permanenceAdministrativeDao.findByemployeDoti(doti);
List<PermanenceAdministrative> resultats = new ArrayList<PermanenceAdministrative>();
permanenceAdministratives.forEach(per ->{
	if(DateUlils.getYear(per.getDate()).equals(annee)) {
		resultats.add(per);
	}
});
	return resultats;
}


}
