package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.SalaireEmploye;

public interface SalaireEmployeService {
	SalaireEmploye findByid(Long id);
	List<SalaireEmploye> findByEmployeId(Long id);
	List<SalaireEmploye> findByEmployeEmail(String email);
	List<SalaireEmploye> findByEmployeDoti(Integer doti);
	List<SalaireEmploye> findBySalaireNet(Double montantDeBase);
	List<SalaireEmploye> findByMonatntModifie(Double montantModifier);
	List<SalaireEmploye> findAll();
	int save(SalaireEmploye salaireEmploye);
	int deleteById(Long id);

}
