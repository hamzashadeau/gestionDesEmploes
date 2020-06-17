package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.itextpdf.text.DocumentException;

public interface NoteGeneraleService {
	NoteGeneralDeAnnee findByid(Long id);
	List<NoteGeneralDeAnnee> findByEmployeDoti(String doti);
	public NoteGeneralDeAnnee findByDateAndEmployeDoti(Date date, String doti);
	List<NoteGeneralDeAnnee> findAll();
	int save(NoteGeneralDeAnnee noteGeneralDeAnnee);
	int deleteById(Long id);
	public List<NoteGeneralDeAnnee> findNoteDeEmploye(String doti);
	List<NoteGeneralDeAnnee> findByEtat(String etat);
	List<NoteGeneralDeAnnee> findNoteNonTraite(String etat);
	public int RapportDesNoteePdf(NoteGeneralDeAnnee note) throws DocumentException, FileNotFoundException;
	public int listeDeEmployeAyantBesoinDUneNoteExcel(List<Employe> employes);
	public int listeDeEmployeAyantBesoinDUneNotePDF(List<Employe> employes ) throws FileNotFoundException, DocumentException;
}
