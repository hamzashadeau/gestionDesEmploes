package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.stock.bean.Departement;
import com.itextpdf.text.DocumentException;

public interface DepartementService {
	Departement findByid(Long id);
	Departement findByNom(String nom);
	Departement findByChefdoti(Integer doti);
	List<Departement> findAll();
	int save(Departement departement);
	int deleteById(Long id);
	int nombreDesDepartements();
	public int listedepartementPdf() throws DocumentException, FileNotFoundException;
}
