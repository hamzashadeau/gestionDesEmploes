package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;
import com.itextpdf.text.DocumentException;

public interface EmployeService {
	Employe findByCin(String cin);
	Employe findByDoti(String doti);
	Employe findByEmail(String email);
	List<Employe> findBySupId(Long id);
	List<Employe> findByDateAvancementPrevue(Date dateAvancementPrevue);
	List<Employe> findByDateDeProchainNote(Date dateDeProchainNote);
	List<Employe> findByDernierGradeGradeLibelle(String libelle);
	List<Employe> findByDateProchainEvaluation(Date dateProchainEvaluation);
	List<Employe> findByDepNom(String nomDepartement);
	List<Employe> findAll();
	int save(Employe employe);
	int deleteById(Long id);
	Employe findByid(Long id);
	int nombreDesEmployes();
	int nombreDesEmployesParDepartements(String nomDepartement);
	public int nombreDesEmployesParAnneeDeEntré(Integer annee);
	public Double MoyenDeSalaireParAnnee(int annee);
	public List<Employe> EmployesParAnneeDeEntré(Integer annee);
	public List<Employe> findLesEmployeAyantEvaluationAujourdHui();
	public List<Employe> findLesEmployeAyantAvancementAujourdHui();
	public List<Employe> findLesEmployeAyantLaNoteGeneraleAujourdHui();
	public List<Employe> findBySoldeRestantesCongeExceptionnel(Integer soldeRestantesCongeExceptionnel);
	public int update(Employe employe);
	public int listeDesEmployePdf() throws DocumentException, FileNotFoundException;
	public int listeDesEmployeDeDepartementPdf(ArrayList<Employe> employes) throws DocumentException, FileNotFoundException;
	public int listeDesEmployeDeGradePdf(ArrayList<Employe> employes) throws DocumentException, FileNotFoundException;
	public List<Employe> getProchaineAvancement();
}
