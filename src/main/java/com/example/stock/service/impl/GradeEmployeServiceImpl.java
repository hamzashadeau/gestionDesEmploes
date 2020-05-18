package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.GradeEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Grade;
import com.example.stock.bean.GradeEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.GradeEmployeService;
import com.example.stock.service.facade.GradeService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class GradeEmployeServiceImpl implements GradeEmployeService {
@Autowired
private GradeEmployeDao gradeDao;
@Autowired
private EmployeService employeService;
@Autowired
private EmployeDao employeDao;
@Autowired
private GradeService gradeService;

@Override
public int save(GradeEmploye grade) {
	if(grade.getId() !=  null) {
return -1;
}else {
	grade.setGrade(gradeService.findByLibelle(grade.getGrade().getLibelle()));
	// ncriw rapport evaluation
	gradeDao.save(grade);
		return 1;
}
	}

@Override
public GradeEmploye findByid(Long id) {
	if (gradeDao.findById(id).isPresent()) {
		return gradeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	gradeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<GradeEmploye> findAll() {
	return gradeDao.findAll();
}


@Override
public List<GradeEmploye> findByDoti(Integer doti) {
	return gradeDao.findByDoti(doti);
}

@Override
public List<GradeEmploye> findByDateDeAffectation(Date dateAfectation) {
	return gradeDao.findByDateDeAffectation(dateAfectation);
}

@Override
public List<GradeEmploye> findByEtat(String etat) {
	return gradeDao.findByEtat(etat);
}
@Override
public List<GradeEmploye> findGradeNonTraite() {
	return gradeDao.findByEtat("en traitement");
}

@Override
public int accepterUnGrade(GradeEmploye gradeEmploye) {
gradeEmploye.setEtat("traité");
gradeEmploye.setDateDeAffectation(new Date());
gradeDao.save(gradeEmploye);
Employe employe = employeService.findByDoti(gradeEmploye.getDoti());
employe.setDernierGrade(gradeEmploye);
employe.setDateProchainEvaluation(DateUlils.getDateEvaluationDeGrade(gradeEmploye));
employe.setDateDeProchainNote(DateUlils.getDateDeNote(gradeEmploye.getDateDeAffectation()));
employeDao.save(employe);
	return 1;
}

@Override
public int update(GradeEmploye grade) {
	grade.setGrade(gradeService.findByLibelle(grade.getGrade().getLibelle()));
	// ncriw rapport evaluation
	gradeDao.save(grade);
	return 1;
}

//liste Des grades de Employe Pdf
public int listeDeGradeDeEmployePdf(List<GradeEmploye> grades) throws DocumentException, FileNotFoundException {
Integer doti = null;
	for (GradeEmploye gradeEmploye : grades) {
	doti = gradeEmploye.getDoti();
}
	Employe employe = employeService.findByDoti(doti);
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(employe.getFullName() + "listeDeGrade.pdf")); 
	
	document.open();
	Image img,img1;
	try {
		img = Image.getInstance("fstgIcone.png");
		img.setAlignment(Element.ALIGN_TOP);
		img.setAlignment(Element.ALIGN_LEFT);
		document.add(img);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	Paragraph p1 = new Paragraph("\n\t liste des grades " + employe.getFullName() + " \n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

  
  PdfPTable table = new PdfPTable(2); // 3 columns.

  PdfPCell cell1 = new PdfPCell(new Paragraph("grade"));
  PdfPCell cell2 = new PdfPCell(new Paragraph("date Affectation"));

  table.addCell(cell1);
  table.addCell(cell2);

	for (GradeEmploye gradeEmploye : grades) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(gradeEmploye.getGrade().getLibelle()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(gradeEmploye.getDateDeAffectation().toString()));
	    table.addCell(cell10);
	    table.addCell(cell11);
	}

document.add(table);
 
 Font f = new Font();
 f.setStyle(Font.BOLD);
 f.setSize(8);
 
 Paragraph p4 = new Paragraph( "\n \r\r\r\r signer :",f);
 p4.setAlignment(Element.ALIGN_RIGHT);
 document.add(p4);

 Paragraph p20 = new Paragraph( "\n \r\r\r\r marakech  le :"+new  Date().toString(),f);
 p20.setAlignment(Element.ALIGN_LEFT);
 document.add(p20);
  document.close();
	return 1;
}	

}
