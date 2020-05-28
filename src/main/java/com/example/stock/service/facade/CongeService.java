package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.example.stock.bean.Congé;
import com.itextpdf.text.DocumentException;

public interface CongeService {
	List<Congé> findByCongeeLibelle(String libelle);
	List<Congé> findByEmployeEmail(String email);
	List<Congé> findByEmployeDoti(String matricule);
	//List<Congé> findByEtat(String etat);
	List<Congé> findAll();
	List<Congé> findByCongeeLibelleAndDateDeDebut(String libelle, Date date);
	int save(Congé conge);
	int deleteById(Long id);
	Congé findByid(Long id);
	public int update(Congé congé);
	public int listeDesCongéPdf(List<Congé> conges) throws DocumentException, FileNotFoundException;
	public List<Congé>  findCongeByAnne(Integer annee, String type);
}
