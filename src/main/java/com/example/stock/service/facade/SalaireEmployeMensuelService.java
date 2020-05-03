package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.SalaireEmploye;
import com.example.stock.bean.SalaireEmployeMensuel;

public interface SalaireEmployeMensuelService {
	SalaireEmployeMensuel findByid(Long id);
	List<SalaireEmployeMensuel> findByEmployeId(Long id);
	List<SalaireEmployeMensuel> findByEmployeEmail(String email);
	List<SalaireEmployeMensuel> findByEmployeDoti(Integer doti);
	List<SalaireEmployeMensuel> findByEmployeDotiAndYearAndMois(Integer doti,Integer year,Integer mois);
	List<SalaireEmployeMensuel> findByMonatntModifie(Double montantModifier);
	List<SalaireEmployeMensuel> findAll();
	int save(SalaireEmployeMensuel salaireEmployeMensuel);
	int deleteById(Long id);

}
