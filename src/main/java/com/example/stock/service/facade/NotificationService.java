package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.TypeNotification;

public interface NotificationService {
	TypeNotification findByType(String type);


	List<TypeNotification> findAll();

	int save(TypeNotification typeNotification);

	int deleteById(Long id);

	public TypeNotification findByid(Long id);

}
