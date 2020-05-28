package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.itextpdf.text.DocumentException;

public interface FormationService {
	List<Formation> findByemployeId(Long id);

	List<Formation> findByemployeEmail(String email);

	List<Formation> findByemployeDoti(String doti);

	List<Formation> findAll();

	int save(Formation congé);

	int deleteById(Long id);

	public Formation findByid(Long id);

	public List<Formation> findFormationDeEmploye(Employe employe);

	public int listeDesFormationsPdf(ArrayList<Formation> formations) throws DocumentException, FileNotFoundException;

	public int update(Formation formation);
}
