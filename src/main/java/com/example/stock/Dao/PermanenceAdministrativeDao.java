package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.bean.Prix;

@Repository
public interface PermanenceAdministrativeDao extends JpaRepository<PermanenceAdministrative, Long> {
	List<PermanenceAdministrative> findByEmployeId(Long id);
	List<PermanenceAdministrative> findByEmployeEmail(String email);
	List<PermanenceAdministrative> findByemployeDoti(Integer doti);
	List<PermanenceAdministrative> findByPeriode(Integer periode);

	
}
