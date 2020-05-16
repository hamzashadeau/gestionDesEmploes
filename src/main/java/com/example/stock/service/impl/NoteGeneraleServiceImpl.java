package com.example.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NoteGeneraleDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NoteService;

@Service
public class NoteGeneraleServiceImpl implements NoteGeneraleService {
@Autowired
private NoteGeneraleDao noteGeneraleDao;
@Autowired
private NoteService noteService;
@Autowired
private EmployeService employeService;

@Override
public int save(NoteGeneralDeAnnee noteGeneralDeAnnee) {
//	if(findByid(noteGeneralDeAnnee.getId())!= null) {
//return -1;
//}else {
	int doti =noteGeneralDeAnnee.getMoyenGeneral().intValue();
	Employe employe = employeService.findByDoti(doti);
	noteGeneralDeAnnee.setEmploye(employe);
employeService.save(employe);
	//noteGeneralDeAnnee.getNoteDeAffectationDesTachesLieeAuTravail().setLibelle("NoteDeAffectationDesTachesLieeAuTravail"+ employe.getDoti() + noteGeneralDeAnnee.getDate().toString() );	
	noteService.save(noteGeneralDeAnnee.getNoteDeAffectationDesTachesLieeAuTravail());
	noteService.save(noteGeneralDeAnnee.getNoteDeCapaciteDeOrganisation());
	noteService.save(noteGeneralDeAnnee.getNoteDeCompotement());
	noteService.save(noteGeneralDeAnnee.getNoteDeRechercheEtDeInnovation());
	noteService.save(noteGeneralDeAnnee.getNoteDeRentabilite());
	//calucler la mention et la moyen
	noteGeneraleDao.save(noteGeneralDeAnnee);
		return 1;
}
	//}

@Override
public NoteGeneralDeAnnee findByid(Long id) {
	if (noteGeneraleDao.findById(id).isPresent()) {
		return noteGeneraleDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	noteGeneraleDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<NoteGeneralDeAnnee> findAll() {
	return noteGeneraleDao.findAll();
}

@Override
public List<NoteGeneralDeAnnee> findByEmployeId(Long id) {
	return noteGeneraleDao.findByEmployeId(id);
}

@Override
public List<NoteGeneralDeAnnee> findByEmployeEmail(String email) {
	return noteGeneraleDao.findByEmployeEmail(email);
}

@Override
public List<NoteGeneralDeAnnee> findByEmployeDoti(Integer doti) {
	return noteGeneraleDao.findByEmployeDoti(doti);
}

@Override
public List<NoteGeneralDeAnnee> findNoteDeEmploye(Employe employe) {
	List<NoteGeneralDeAnnee> punitionEmployes = findByEmployeDoti(employe.getDoti());
	List<NoteGeneralDeAnnee> resultat = new ArrayList<NoteGeneralDeAnnee>();
	for (NoteGeneralDeAnnee punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getDate()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

@Override
public NoteGeneralDeAnnee findByDateAndEmployeDoti(Date date, Integer doti) {
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//String strDate1 = formatter.format(date);
//Date date1 = formatter.parse(strDate1);
	//System.out.println(date1);
	return noteGeneraleDao.findByDateAndEmployeDoti(date, doti);
}

@Override
public List<NoteGeneralDeAnnee> findByEtat(String etat) {
	return noteGeneraleDao.findByEtat(etat);
}
}
