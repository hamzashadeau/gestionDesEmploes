package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.DemaneDeDocument;

public interface DemandeDeDocumentService {
	List<DemaneDeDocument> findByEmployeId(Long id);
	List<DemaneDeDocument> findByEmployeEmail(String email);
	List<DemaneDeDocument> findByEmployeDoti(Integer doti);
	List<DemaneDeDocument> findByEtat(String etat);
	List<DemaneDeDocument> findAll();
	int save(DemaneDeDocument demaneDeDocument);
	int deleteById(Long id);
	DemaneDeDocument findByid(Long id);
}
