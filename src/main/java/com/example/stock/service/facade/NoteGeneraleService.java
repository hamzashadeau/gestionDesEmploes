package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;

public interface NoteGeneraleService {
	NoteGeneralDeAnnee findByid(Long id);
	List<NoteGeneralDeAnnee> findByEmployeId(Long id);
	List<NoteGeneralDeAnnee> findByEmployeEmail(String email);
	List<NoteGeneralDeAnnee> findByEmployeDoti(Integer doti);
	public NoteGeneralDeAnnee findByDateAndEmployeDoti(Date date, Integer doti);
	List<NoteGeneralDeAnnee> findAll();
	int save(NoteGeneralDeAnnee noteGeneralDeAnnee);
	int deleteById(Long id);
	public List<NoteGeneralDeAnnee> findNoteDeEmploye(Employe employe);
	List<NoteGeneralDeAnnee> findByEtat(String etat);

}
