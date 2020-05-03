package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Emoluments;

public interface EmolumentsService {
	List<Emoluments> findByMontant(Double montant);
	List<Emoluments> findByLibelle(String libelle);
	List<Emoluments> findAll();
	int save(Emoluments emoluments);
	int deleteById(Long id);
	Emoluments findByid(Long id);
}
