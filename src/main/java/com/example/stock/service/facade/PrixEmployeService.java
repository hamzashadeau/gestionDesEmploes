package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.PrixEmploye;

public interface PrixEmployeService {
	PrixEmploye findByid(Long id);
	List<PrixEmploye> findByEmployeEmail(String email);
	List<PrixEmploye> findByPrixLibelle(String libelle);
	List<PrixEmploye> findAll();
	int save(PrixEmploye prixEmploye);
	int deleteById(Long id);
	public List<PrixEmploye> findPrixDeEmploye(Employe employe);

}
