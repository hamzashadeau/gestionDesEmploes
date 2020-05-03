package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.PermanenceAdministrative;

public interface PermanenceAdministrativeService {
	PermanenceAdministrative findByid(Long id);
	List<PermanenceAdministrative> findByEmployeId(Long id);
	List<PermanenceAdministrative> findByEmployeEmail(String email);
	List<PermanenceAdministrative> findByemployeDoti(Integer doti);
	List<PermanenceAdministrative> findByPeriode(Integer periode);	
	List<PermanenceAdministrative> findAll();
	int save(PermanenceAdministrative permanenceAdministrative);
	int deleteById(Long id);

}
