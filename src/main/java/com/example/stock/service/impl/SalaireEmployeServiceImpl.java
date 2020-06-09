package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.SalaireEmployeDao;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.SalaireEmployeService;

@Service
public class SalaireEmployeServiceImpl implements SalaireEmployeService {
@Autowired
private SalaireEmployeDao salaireEmployeDao;
@Autowired
private NotificationEmployeService notificationEmployeService;
@Autowired
private NotificationService notificationService;

@Override
public int save(SalaireEmploye salaireEmploye) {
	if(salaireEmploye.getId()!= null) {
return -1;
}else {
	Notification notification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, salaireEmploye.getEmploye(), new Date(), "save salaire employe ");
	notificationEmployeService.save(notificationEmploye);
	salaireEmployeDao.save(salaireEmploye);
		return 1;
}
	}
@Override
public int update(SalaireEmploye salaireEmploye) {
	if(salaireEmploye.getId() == null) {
return -1;
}else {
	Notification notification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, salaireEmploye.getEmploye(), new Date(), "update salaire employe ");
	notificationEmployeService.save(notificationEmploye);
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
	SalaireEmploye salaireEmploye = findByid(id);
	Notification notification = notificationService.findByType("delete");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, salaireEmploye.getEmploye(), new Date(), "delete salaire employe ");
	notificationEmployeService.save(notificationEmploye);
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
public SalaireEmploye findByEmployeId(Long id) {
	return salaireEmployeDao.findByEmployeId(id);
}

@Override
public SalaireEmploye findByEmployeEmail(String email) {
	return salaireEmployeDao.findByEmployeEmail(email);
}


@Override
public List<SalaireEmploye> findByMonatntModifie(Double montantModifier) {
	return salaireEmployeDao.findByMonatntModifie(montantModifier);
}

@Override
public SalaireEmploye findByEmployeDoti(String doti) {
	return salaireEmployeDao.findByEmployeDoti(doti);
}

@Override
public List<SalaireEmploye> findBySalaireNet(Double montantDeBase) {
	return salaireEmployeDao.findBySalaireNet(montantDeBase);
}

}
