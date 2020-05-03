package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Notification;

public interface NotificationService {
	Notification findByType(String type);


	List<Notification> findAll();

	int save(Notification notification);

	int deleteById(Long id);

	public Notification findByid(Long id);

}
