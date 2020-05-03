package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NotificationDao;
import com.example.stock.bean.Notification;
import com.example.stock.service.facade.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
@Autowired
private NotificationDao notificationDao;


@Override
public int save(Notification notification) {
	if(findByid(notification.getId())!= null) {
return -1;
}else {
	notificationDao.save(notification);
		return 1;
}
	}

@Override
public Notification findByid(Long id) {
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
public Notification findByType(String type) {
	return notificationDao.findByType(type);
}

@Override
public List<Notification> findAll() {
	return notificationDao.findAll();
}

}
