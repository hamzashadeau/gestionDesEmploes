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
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.NoteGeneraleService;

@Service
public class NoteGeneraleServiceImpl implements NoteGeneraleService {
@Autowired
private NoteGeneraleDao noteGeneraleDao;


@Override
public int save(NoteGeneralDeAnnee noteGeneralDeAnnee) {
	if(findByid(noteGeneralDeAnnee.getId())!= null) {
return -1;
}else {
	noteGeneraleDao.save(noteGeneralDeAnnee);
		return 1;
}
	}

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
public List<NoteGeneralDeAnnee> findByEmployéId(Long id) {
	return noteGeneraleDao.findByEmployéId(id);
}

@Override
public List<NoteGeneralDeAnnee> findByEmployéEmail(String email) {
	return noteGeneraleDao.findByEmployéEmail(email);
}

@Override
public List<NoteGeneralDeAnnee> findByEmployéDoti(Integer doti) {
	return noteGeneraleDao.findByEmployéDoti(doti);
}

@Override
public List<NoteGeneralDeAnnee> findByDate(Date date) {
	return noteGeneraleDao.findByDate(date);
}
@Override
public List<NoteGeneralDeAnnee> findPunitionDeEmploye(Employe employe) {
	List<NoteGeneralDeAnnee> punitionEmployes = findByEmployéDoti(employe.getDoti());
	List<NoteGeneralDeAnnee> resultat = new ArrayList<NoteGeneralDeAnnee>();
	for (NoteGeneralDeAnnee punitionEmploye : punitionEmployes) {
		if(DateUlils.VerifieDate(punitionEmploye.getDate()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}
}
