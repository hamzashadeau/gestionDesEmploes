package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Revenu;

public interface RevenuService {
	Revenu findByid(Long id);
	List<Revenu> findByMontant(Double montant);
	List<Revenu> findByLibelle(String libelle);
	List<Revenu> findAll();
	int save(Revenu revenu);
	int deleteById(Long id);

}
