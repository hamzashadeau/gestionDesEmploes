package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.stock.bean.DemaneDeDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface DemandeDeDocumentService {
	List<DemaneDeDocument> findByEmployeId(Long id);
	List<DemaneDeDocument> findByEmployeEmail(String email);
	List<DemaneDeDocument> findByEmployeDoti(Integer doti);
	List<DemaneDeDocument> findByEtat(String etat);
	List<DemaneDeDocument> findAll();
	int save(DemaneDeDocument demaneDeDocument);
	public String hellowordl() throws DocumentException, FileNotFoundException;
	int deleteById(Long id);
	DemaneDeDocument findByid(Long id);
}
