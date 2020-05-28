package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Employe;
import com.example.stock.bean.Fonction;
import com.example.stock.bean.SalaireEmploye;

@Repository
public interface SalaireEmployeDao extends JpaRepository<SalaireEmploye, Long> {
	SalaireEmploye findByEmployeId(Long id);
	SalaireEmploye findByEmployeEmail(String email);
	SalaireEmploye findByEmployeDoti(String doti);
	List<SalaireEmploye> findBySalaireNet(Double montantDeBase);
	List<SalaireEmploye> findByMonatntModifie(Double montantModifier);
	

}
