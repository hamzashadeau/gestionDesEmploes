package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.DepFonction;

public interface DepFonctionService {
	DepFonction findByid(Long id);
	List<DepFonction> findByDepartemantNom(String nomDepartemant);
	List<DepFonction> findByFonctionLibelle(String libelle);
	List<DepFonction> findAll();
	int save(DepFonction employe);
	int deleteById(Long id);

}
