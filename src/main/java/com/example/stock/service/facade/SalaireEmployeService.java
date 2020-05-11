package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.SalaireEmploye;

public interface SalaireEmployeService {
	SalaireEmploye findByid(Long id);
	SalaireEmploye findByEmployeId(Long id);
	SalaireEmploye findByEmployeEmail(String email);
	SalaireEmploye findByEmployeDoti(Integer doti);
	List<SalaireEmploye> findBySalaireNet(Double montantDeBase);
	List<SalaireEmploye> findByMonatntModifie(Double montantModifier);
	List<SalaireEmploye> findAll();
	int save(SalaireEmploye salaireEmploye);
	int deleteById(Long id);

}
