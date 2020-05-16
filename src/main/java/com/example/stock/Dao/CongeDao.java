package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Congé;

@Repository
public interface CongeDao extends JpaRepository<Congé, Long> {
	List<Congé> findByCongeeLibelle(String libelle);
	List<Congé> findByEmployeEmail(String email);
	List<Congé> findByEmployeDoti(Integer matricule);
	//List<Congé> findByEtat(String etat);
}
