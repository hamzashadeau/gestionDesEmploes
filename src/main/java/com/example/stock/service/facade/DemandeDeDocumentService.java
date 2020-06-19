package com.example.stock.service.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.transform.TransformerException;

import org.springframework.web.multipart.MultipartFile;

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
	public int listeDesDemandesExcel(List<DemaneDeDocument> demandes);
	int deleteById(Long id);
	public int attestationDeTravail(DemaneDeDocument demaneDeDocument) throws DocumentException, TransformerException, AddressException, MessagingException, IOException;
	DemaneDeDocument findByid(Long id);
	public int rapportPdf(RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException;
	public int attestationDeSalaire(DemaneDeDocument demaneDeDocument) throws DocumentException, TransformerException, AddressException, MessagingException, IOException;
	public int infoEmployePdf(Employe employe) throws DocumentException, FileNotFoundException;
	public int listeDesDemandePdf(List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException;
	List<DemaneDeDocument> findByTypeDeDocumentLibelleAndEmployeDoti(String libelle, String doti);
	public int  sendmail(String email, String subject,String content,MultipartFile file) throws AddressException, MessagingException, IOException, TransformerException;

}
