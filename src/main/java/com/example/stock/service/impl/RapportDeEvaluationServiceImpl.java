package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.RapportDeEvaluationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.FormationService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
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
@Autowired
private NotificationEmployeService notificationEmployeService;
@Autowired
private NotificationService notificationService;
@Autowired
private EmployeService employeService;
@Override
public int save(RapportDeEvaluation rapportDeEvaluation) {
	if(rapportDeEvaluation.getId() != null) {
return -1;
}else {
	rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	rapportDeEvaluation.setMoyen(getMoyenNote(rapportDeEvaluation.getNoteGenerale()));
	rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));
	rapportDeEvaluationDao.save(rapportDeEvaluation);
	TypeNotification typeNotification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, rapportDeEvaluation.getEmploye(), new Date(), "save rapport ");
	notificationEmployeService.save(notificationEmploye);
	return 1;
		}
}
@Override
public int update(RapportDeEvaluation rapportDeEvaluation) {
	if(rapportDeEvaluation.getId() == null) {
return -1;
}else {
	//Employe employe = employeService.findByDoti(rapportDeEvaluation.getEmploye().getDoti());
	//rapportDeEvaluation.setEmploye(employe);
//	rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	//rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	//rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	//rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(rapportDeEvaluation.getEmploye().getDoti()));
	//rapportDeEvaluation.setMoyen(getMoyenNote(rapportDeEvaluation.getNoteGenerale()));
	//rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));
	//rapportDeEvaluation.setDoti(null);
	System.out.println(rapportDeEvaluation.getId());
	System.out.println(rapportDeEvaluation);
	rapportDeEvaluationDao.save(rapportDeEvaluation);
	TypeNotification typeNotification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, rapportDeEvaluation.getEmploye(), new Date(), "update rapport ");
	notificationEmployeService.save(notificationEmploye);
	return 1;
		}
}

//getMoyenNote
public Double getMoyenNote(List<NoteGeneralDeAnnee> notes) {
Double somme = 0.0;
for (NoteGeneralDeAnnee noteGeneralDeAnnee : notes) {
	somme += noteGeneralDeAnnee.getMoyenGeneral();
}
return somme/notes.size();
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
	RapportDeEvaluation rapportDeEvaluation = findByid(id);
	TypeNotification typeNotification = notificationService.findByType("delete");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, rapportDeEvaluation.getEmploye(), new Date(), "delete rapport ");
	notificationEmployeService.save(notificationEmploye);
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
public List<RapportDeEvaluation> findByEmployeDoti(String doti) {
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
public RapportDeEvaluation findByNouveauGradeIdAndEmployeDoti(Long id, String doti) {
	return rapportDeEvaluationDao.findByNouveauGradeIdAndEmployeDoti(id, doti);
}





}
