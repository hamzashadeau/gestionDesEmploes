package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NotificationDao;
import com.example.stock.bean.TypeNotification;
import com.example.stock.service.facade.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
@Autowired
private NotificationDao notificationDao;


@Override
public int save(TypeNotification typeNotification) {
	if(findByid(typeNotification.getId())!= null) {
return -1;
}else {
	notificationDao.save(typeNotification);
		return 1;
}
	}

@Override
public TypeNotification findByid(Long id) {
	if (notificationDao.findById(id).isPresent()) {
		return notificationDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	notificationDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public TypeNotification findByType(String type) {
	return notificationDao.findByType(type);
}

@Override
public List<TypeNotification> findAll() {
	return notificationDao.findAll();
}

}
