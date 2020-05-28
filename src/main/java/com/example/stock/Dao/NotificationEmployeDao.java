package com.example.stock.Dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.NotificationEmploye;

@Repository
public interface NotificationEmployeDao extends JpaRepository<NotificationEmploye, Long> {
List<NotificationEmploye> findByEmployeId(Long id);
List<NotificationEmploye> findByEmployeEmail(String email);
List<NotificationEmploye> findByemployeDoti(String doti);
List<NotificationEmploye> findByDateDeNotification(Date dateDeObtenation);



}
