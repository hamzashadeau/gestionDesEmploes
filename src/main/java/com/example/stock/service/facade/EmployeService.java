package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;

public interface EmployeService {
	Employe findByCin(Integer cin);
	Employe findByDoti(Integer doti);
	Employe findByEmail(String email);
	List<Employe> findBySupId(Long id);
	List<Employe> findByDateAvancementPrevue(Date dateAvancementPrevue);
	List<Employe> findByDateDeProchainNote(Date dateDeProchainNote);
	List<Employe> findByDateProchainEvaluation(Date dateProchainEvaluation);
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
	 List<Employe> findBySoldeRestantesCongéExceptionnel(Integer soldeRestantesCongéExceptionnel);

}
