package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;

public interface NoteGeneraleService {
	NoteGeneralDeAnnee findByid(Long id);
	List<NoteGeneralDeAnnee> findByEmployéId(Long id);
	List<NoteGeneralDeAnnee> findByEmployéEmail(String email);
	List<NoteGeneralDeAnnee> findByEmployéDoti(Integer doti);
	List<NoteGeneralDeAnnee> findByDate(Date date);
	List<NoteGeneralDeAnnee> findAll();
	int save(NoteGeneralDeAnnee noteGeneralDeAnnee);
	int deleteById(Long id);
	public List<NoteGeneralDeAnnee> findPunitionDeEmploye(Employe employe);
}
