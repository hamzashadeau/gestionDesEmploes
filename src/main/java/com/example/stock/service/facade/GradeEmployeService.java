package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.GradeEmploye;
import com.itextpdf.text.DocumentException;

public interface GradeEmployeService {

	List<GradeEmploye> findByDoti(Integer doti);

	List<GradeEmploye> findByDateDeAffectation(Date dateAfectation);

	List<GradeEmploye> findAll();

	int save(GradeEmploye gradeEmploye);

	int deleteById(Long id);

	public GradeEmploye findByid(Long id);

	List<GradeEmploye> findByEtat(String etat);

	public List<GradeEmploye> findGradeNonTraite();

	public int creeUnGradeNonTraite(Integer doti);

	public int accepterUnGrade(GradeEmploye gradeEmploye);

	public int update(GradeEmploye gradeEmploye);

	public int listeDeGradeDeEmployePdf(List<GradeEmploye> grades) throws DocumentException, FileNotFoundException;
}
