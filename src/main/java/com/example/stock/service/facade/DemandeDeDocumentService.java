package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.transform.TransformerException;

import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.RapportDeEvaluation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface DemandeDeDocumentService {
	List<DemaneDeDocument> findByEmployeId(Long id);
	List<DemaneDeDocument> findByEmployeEmail(String email);
	List<DemaneDeDocument> findByEmployeDoti(String doti);
	List<DemaneDeDocument> findByEtat(String etat);
	List<DemaneDeDocument> findAll();
	public int update(DemaneDeDocument demaneDeDocument);
	int save(DemaneDeDocument demaneDeDocument);
	int deleteById(Long id);
	DemaneDeDocument findByid(Long id);
	public int rapportPdf(RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException;
	public int attestationDeSalaire(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException, TransformerException;	
	public int infoEmployePdf(Employe employe) throws DocumentException, FileNotFoundException;
	public int listeDesDemandePdf(List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException;
	public int attestationDeTravail(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException, TransformerException;
}
