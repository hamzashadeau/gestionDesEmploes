package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Fonction;

public interface FonctionService {
	Fonction findByid(Long id);
	Fonction findByLibelle(String libelle);
	List<Fonction> findAll();
	int save(Fonction fonction);
	int deleteById(Long id);

}
