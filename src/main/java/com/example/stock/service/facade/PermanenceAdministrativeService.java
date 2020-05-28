package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.PermanenceAdministrative;

public interface PermanenceAdministrativeService {
	PermanenceAdministrative findByid(Long id);
	List<PermanenceAdministrative> findByEmployeId(Long id);
	List<PermanenceAdministrative> findByEmployeEmail(String email);
	List<PermanenceAdministrative> findByemployeDoti(String doti);
	List<PermanenceAdministrative> findByPeriode(Integer periode);	
	List<PermanenceAdministrative> findAll();
	PermanenceAdministrative findByDate(Date date);
	int save(PermanenceAdministrative permanenceAdministrative);
	int deleteById(Long id);

}
