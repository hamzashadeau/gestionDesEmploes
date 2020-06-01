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
import com.example.stock.Dao.RapportDeEvaluationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Grade;
import com.example.stock.bean.GradeEmploye;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.FormationService;
import com.example.stock.service.facade.GradeEmployeService;
import com.example.stock.service.facade.GradeService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.PrixEmployeService;
import com.example.stock.service.facade.PunitionEmployeService;
import com.example.stock.service.facade.RapportDeEvaluationService;
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
@Autowired
private GradeEmployeService gradeEmployeService;
@Autowired
private NoteGeneraleService noteGeneraleService;
@Autowired
private FormationService formationService;
@Autowired
private PunitionEmployeService punitionEmployeService;
@Autowired
private PrixEmployeService prixEmployeService;
@Autowired
private RapportDeEvaluationService rapportDeEvaluationService;
@Autowired
private RapportDeEvaluationDao rapportDeEvaluationDao;
@Autowired
private NotificationEmployeService notificationEmployeService;
@Autowired
private NotificationService notificationService;
@Override
public int save(GradeEmploye gradeEmploye) {
	Employe employe = employeService.findByDoti(gradeEmploye.getDoti());
	Grade grade = gradeService.findByLibelle(gradeEmploye.getGrade().getLibelle());
	System.out.println(gradeEmploye.getGrade().getLibelle());
	if(gradeEmploye.getId() !=  null) {
return -1;
}else if (employe == null) {
	return -2;
} else if (grade == null) {
	return -3;
}else {
	gradeEmploye.setDoti(employe.getDoti());
	gradeEmploye.setGrade(grade);
	gradeEmploye.setEtat("en traitement");
	gradeDao.save(gradeEmploye);
	//rapport evaluation
	RapportDeEvaluation rapportDeEvaluation = new RapportDeEvaluation();
	rapportDeEvaluation.setEmploye(employe);
	rapportDeEvaluation.setNouveauGrade(gradeEmploye);
				// notes 
rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe));
				//formation
rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe));
				//Punition
rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe));
				//prix
rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe));
				//moyen
rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe)));
//moyen
rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));
rapportDeEvaluationService.save(rapportDeEvaluation);
Notification notification = notificationService.findByType("save");
NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "save grade employe");
notificationEmployeService.save(notificationEmploye);
		return 1;
}
	}
public int  creeUnGradeNonTraite(String doti) {
GradeEmploye gradeEmploye = new GradeEmploye();
Employe employe = employeService.findByDoti(doti);
	gradeEmploye.setDoti(employe.getDoti());
	gradeEmploye.setGrade(gradeService.findByLibelle(DateUlils.getNouvauGrade(employe.getDernierGrade().getGrade())));
	gradeEmploye.setEtat("en traitement");
	gradeDao.save(gradeEmploye);
	//rapport evaluation
	RapportDeEvaluation rapportDeEvaluation = new RapportDeEvaluation();
	rapportDeEvaluation.setEmploye(employe);
	rapportDeEvaluation.setNouveauGrade(gradeEmploye);
	if(noteGeneraleService.findNoteDeEmploye(employe)!= null) {
		//note
		rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe));
		//moyen
rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe)));
//moyen
rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));

	} if(formationService.findFormationDeEmploye(employe)!= null) {
		//formation
		rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe));
	}	if(punitionEmployeService.findPunitionDeEmploye(employe)!= null) {
		//punition
		rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe));
	}	if(prixEmployeService.findPrixDeEmploye(employe)!= null) {
		//prix
		rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe));
	}
rapportDeEvaluationDao.save(rapportDeEvaluation);
	return 1;
}
//getMoyenNote
public Double getMoyenNote(List<NoteGeneralDeAnnee> notes) {
Double somme = 0.0;
for (NoteGeneralDeAnnee noteGeneralDeAnnee : notes) {
	System.out.println("ha note" + noteGeneralDeAnnee.getMoyenGeneral());
	somme += noteGeneralDeAnnee.getMoyenGeneral();
}
return somme/notes.size();
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
	 GradeEmploye gradeEmploye = gradeEmployeService.findByid(id);
	 Employe employe = employeService.findByDoti(gradeEmploye.getDoti());
	 rapportDeEvaluationService.deleteById(rapportDeEvaluationService.findByNouveauGradeIdAndEmployeDoti(id, gradeEmploye.getDoti()).getId());
		Notification notification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "delete grade employe");
		notificationEmployeService.save(notificationEmploye);
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
public List<GradeEmploye> findByDoti(String doti) {
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
	Employe employe = employeService.findByDoti(grade.getDoti());
	Grade grade2 = gradeService.findByLibelle(grade.getGrade().getLibelle());
	grade.setGrade(grade2);
	grade.setDoti(employe.getDoti());
	Notification notification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "update grade employe");
	notificationEmployeService.save(notificationEmploye);
	// ncriw rapport evaluation
	gradeDao.save(grade);
	return 1;
}

//liste Des grades de Employe Pdf
public int listeDeGradeDeEmployePdf(List<GradeEmploye> grades) throws DocumentException, FileNotFoundException {
	String doti = null;
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
	Notification notification = notificationService.findByType("imprimer");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "imprimer liste grade employe");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}	

}
