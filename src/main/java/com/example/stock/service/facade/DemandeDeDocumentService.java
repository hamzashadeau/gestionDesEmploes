package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.RapportDeEvaluation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface DemandeDeDocumentService {
	List<DemaneDeDocument> findByEmployeId(Long id);
	List<DemaneDeDocument> findByEmployeEmail(String email);
	List<DemaneDeDocument> findByEmployeDoti(Integer doti);
	List<DemaneDeDocument> findByEtat(String etat);
	List<DemaneDeDocument> findAll();
	public int update(DemaneDeDocument demaneDeDocument);
	int save(DemaneDeDocument demaneDeDocument);
	public int attestationDeTravail(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException;
	int deleteById(Long id);
	DemaneDeDocument findByid(Long id);
	public int rapportPdf(RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException;
	public int attestationDeSalaire(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException;
	public int infoEmployePdf(Employe employe) throws DocumentException, FileNotFoundException;
	public int listeDesDemandePdf(List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException;
	
}
