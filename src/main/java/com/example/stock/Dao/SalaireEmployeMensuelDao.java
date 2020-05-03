package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.SalaireEmployeMensuel;

@Repository
public interface SalaireEmployeMensuelDao extends JpaRepository<SalaireEmployeMensuel, Long> {
	List<SalaireEmployeMensuel> findByEmployeId(Long id);
	List<SalaireEmployeMensuel> findByEmployeEmail(String email);
	List<SalaireEmployeMensuel> findByEmployeDoti(Integer doti);
	List<SalaireEmployeMensuel> findByEmployeDotiAndYearAndMois(Integer doti,Integer year,Integer mois);
	List<SalaireEmployeMensuel> findByMonatntModifie(Double montantModifier);
}
