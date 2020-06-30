package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.CongéEmployeSalaire;

public interface CongeEmployeService {
	List<CongéEmployeSalaire> findByCongeId(Long id);
	CongéEmployeSalaire findByid(Long id);
	List<CongéEmployeSalaire> findAll();
	int save(CongéEmployeSalaire congéEmployeSalaire);
	int deleteById(Long id);

}
