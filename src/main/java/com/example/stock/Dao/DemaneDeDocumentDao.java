package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.DemaneDeDocument;

@Repository
public interface DemaneDeDocumentDao extends JpaRepository<DemaneDeDocument, Long> {
	List<DemaneDeDocument> findByEmployeId(Long id);
	List<DemaneDeDocument> findByEmployeEmail(String email);
	List<DemaneDeDocument> findByEmployeDoti(String doti);
	List<DemaneDeDocument> findByEtat(String etat);
	List<DemaneDeDocument> findByTypeDeDocumentLibelleAndEmployeDoti(String libelle, String doti);
}
