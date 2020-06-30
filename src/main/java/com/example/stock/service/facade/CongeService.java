package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.transform.TransformerException;

import com.example.stock.bean.Congé;
import com.itextpdf.text.DocumentException;

public interface CongeService {
	List<Congé> findByCongeeLibelle(String libelle);
	List<Congé> findByEmployeEmail(String email);
	List<Congé> findByEmployeDoti(String matricule);
	List<Congé> findByEmployeDotiAndCongeeLibelle(String matricule, String libelle);
	//List<Congé> findByEtat(String etat);
	List<Congé> findAll();
	public List<Congé> findByCongeeLibelleAndDateDeDebut(String libelle, Date date1, Date date2);
	public int save(Congé congé) throws AddressException, MessagingException, IOException, TransformerException;
	public int listeDesCongesExcel(List<Congé> conges);
	int deleteById(Long id);
	Congé findByid(Long id);
	public int update(Congé congé);
	public int listeDesCongéPdf(List<Congé> conges) throws DocumentException, FileNotFoundException;
	public List<Congé>  findCongeByAnne(Integer annee, String type);
	public List<Congé> findListeCertificatByAnnee();
	public List<Congé> findCongeCertificat();
	public void resetSoldeCongéEmploye();
	public int AutoRestSoldeCongeEmplye();
	public int listeDesCertificatsPdf(List<Congé> conges) throws DocumentException, FileNotFoundException;
	public int listeDesCertificatsExcel(List<Congé> conges);

}
