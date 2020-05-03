package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.PunitionEmploye;

public interface PunitionEmployeService {
	PunitionEmploye findByid(Long id);
	List<PunitionEmploye> findByEmployeEmail(String email);
	List<PunitionEmploye> findByPunitionLibelle(String libelle);
	List<PunitionEmploye> findByPunitionType(String type);
	List<PunitionEmploye> findByEmployeDoti(Integer doti);
	List<PunitionEmploye> findByEmployeId(Long id);
	List<PunitionEmploye> findAll();
	int save(PunitionEmploye punitionEmploye);
	int deleteById(Long id);
	public List<PunitionEmploye> findPunitionDeEmploye(Employe employe);

}
