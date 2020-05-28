package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.PrixEmploye;
import com.itextpdf.text.DocumentException;

public interface PrixEmployeService {
	PrixEmploye findByid(Long id);

	List<PrixEmploye> findByEmployeDoti(String doti);

	List<PrixEmploye> findByPrixLibelle(String libelle);

	List<PrixEmploye> findAll();

	int save(PrixEmploye prixEmploye);

	int update(PrixEmploye prixEmploye);

	int deleteById(Long id);

	public List<PrixEmploye> findPrixDeEmploye(Employe employe);

	public int listeDesPrixPdf(ArrayList<PrixEmploye> prixEmployes) throws DocumentException, FileNotFoundException;
}
