package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.RapportDeEvaluationDao;
import com.example.stock.bean.Formation;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.FormationService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.PrixEmployeService;
import com.example.stock.service.facade.PrixService;
import com.example.stock.service.facade.PunitionEmployeService;
import com.example.stock.service.facade.RapportDeEvaluationService;

@Service
public class RapportDeEvaluationServiceImpl implements RapportDeEvaluationService {
@Autowired
private RapportDeEvaluationDao rapportDeEvaluationDao;
@Autowired
private FormationService formationService;
@Autowired
private PrixEmployeService prixEmployeService;
@Autowired
private PunitionEmployeService punitionEmployeService;
@Autowired
private NoteGeneraleService noteGeneraleService;
@Override
public int save(RapportDeEvaluation rapportDeEvaluation) {
	if(rapportDeEvaluation.getId()!= null) {
return -1;
}else {
	List<PrixEmploye> prixEmployes = rapportDeEvaluation.getPrix();
	for (PrixEmploye prixEmploye : prixEmployes) {
		if(prixEmploye.getId() == null) {
		prixEmployeService.save(prixEmploye);
	} else {
		prixEmployeService.update(prixEmploye);

	}		
		}
	List<PunitionEmploye> punitionEmployes = rapportDeEvaluation.getPunition();
	for (PunitionEmploye punitionEmploye : punitionEmployes) {
		if(punitionEmploye.getId() == null) {
			punitionEmployeService.save(punitionEmploye);
	} else {
		punitionEmployeService.save(punitionEmploye);
	}
	}
	Double somme = 0.0;
	List<NoteGeneralDeAnnee> noteGeneralDeAnnees = rapportDeEvaluation.getNoteGenerale();
	for (NoteGeneralDeAnnee noteGeneralDeAnnee : noteGeneralDeAnnees) {
		if(noteGeneralDeAnnee.getId() == null) {
			somme += noteGeneralDeAnnee.getMoyenGeneral();
			noteGeneraleService.save(noteGeneralDeAnnee);	
			} else {
				somme += noteGeneralDeAnnee.getMoyenGeneral();
				noteGeneraleService.save(noteGeneralDeAnnee);// a changé
	}
	}
	rapportDeEvaluation.setMoyen(somme/noteGeneralDeAnnees.size());
	List<Formation> formations = rapportDeEvaluation.getFormation();
	for (Formation formation : formations) {
		if(formation.getId() == null) {
			formationService.save(formation);
	} else {
		formationService.update(formation);
	}
	}
	}
	return 1;
}

@Override
public int update(RapportDeEvaluation rapportDeEvaluation) {
	if(rapportDeEvaluation.getId()== null) {
return -1;
}else {
	List<PrixEmploye> prixEmployes = rapportDeEvaluation.getPrix();
	for (PrixEmploye prixEmploye : prixEmployes) {
		if(prixEmploye.getId() == null) {
		prixEmployeService.save(prixEmploye);
	} else {
		prixEmployeService.update(prixEmploye);

	}		
		}
	List<PunitionEmploye> punitionEmployes = rapportDeEvaluation.getPunition();
	for (PunitionEmploye punitionEmploye : punitionEmployes) {
		if(punitionEmploye.getId() == null) {
			punitionEmployeService.save(punitionEmploye);
	} else {
		punitionEmployeService.save(punitionEmploye);
	}
	}
	Double somme = 0.0;
	List<NoteGeneralDeAnnee> noteGeneralDeAnnees = rapportDeEvaluation.getNoteGenerale();
	for (NoteGeneralDeAnnee noteGeneralDeAnnee : noteGeneralDeAnnees) {
		if(noteGeneralDeAnnee.getId() == null) {
			somme += noteGeneralDeAnnee.getMoyenGeneral();
			noteGeneraleService.save(noteGeneralDeAnnee);	
			} else {
				somme += noteGeneralDeAnnee.getMoyenGeneral();
				noteGeneraleService.save(noteGeneralDeAnnee);// a changé
	}
	}
	rapportDeEvaluation.setMoyen(somme/noteGeneralDeAnnees.size());
	List<Formation> formations = rapportDeEvaluation.getFormation();
	for (Formation formation : formations) {
		if(formation.getId() == null) {
			formationService.save(formation);
	} else {
		formationService.update(formation);
	}
	}
	}
	return 1;
}

@Override
public RapportDeEvaluation findByid(Long id) {
	if (rapportDeEvaluationDao.findById(id).isPresent()) {
		return rapportDeEvaluationDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	rapportDeEvaluationDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<RapportDeEvaluation> findAll() {
	return rapportDeEvaluationDao.findAll();
}

@Override
public List<RapportDeEvaluation> findByEmployeEmail(String email) {
	return rapportDeEvaluationDao.findByEmployeEmail(email);
}


@Override
public List<RapportDeEvaluation> findByEmployeDoti(Integer doti) {
	return rapportDeEvaluationDao.findByEmployeDoti(doti);
}

@Override
public List<RapportDeEvaluation> findByEmployeId(Long id) {
	return rapportDeEvaluationDao.findByEmployeId(id);
}

@Override
public List<RapportDeEvaluation> findByNouveauGradeId(Long id) {
	return rapportDeEvaluationDao.findByNouveauGradeId(id);
}

@Override
public RapportDeEvaluation findByNouveauGradeIdAndEmployeDoti(Long id, Integer doti) {
	return rapportDeEvaluationDao.findByNouveauGradeIdAndEmployeDoti(id, doti);
}





}
