package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Punition;

public interface PunitionService {
	Punition findByid(Long id);
	List<Punition> findByType(String type);
	Punition findByLibelle(String libelle);
	List<Punition> findAll();
	int save(Punition punition);
	int deleteById(Long id);

}
