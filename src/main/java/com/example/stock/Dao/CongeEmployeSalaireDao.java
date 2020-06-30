package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.CongéEmployeSalaire;

@Repository
public interface CongeEmployeSalaireDao extends JpaRepository<CongéEmployeSalaire, Long> {
	List<CongéEmployeSalaire> findByCongeId(Long id);
	//List<Congé> findByEtat(String etat);
}
