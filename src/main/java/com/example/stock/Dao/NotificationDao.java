package com.example.stock.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Fonction;
import com.example.stock.bean.Notification;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long> {
Notification findByType(String type);

}
