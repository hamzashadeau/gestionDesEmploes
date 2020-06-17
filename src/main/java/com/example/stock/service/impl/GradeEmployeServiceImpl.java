package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.GradeEmployeDao;
import com.example.stock.Dao.RapportDeEvaluationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Grade;
import com.example.stock.bean.GradeEmploye;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.service.facade.EmolumentsService;
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
import com.example.stock.service.facade.RevenuService;
import com.example.stock.service.facade.SalaireEmployeService;
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
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
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
@Autowired
private SalaireEmployeService salaireEmployeService;
@Autowired
private EmolumentsService emolumentsService;
@Autowired
private RevenuService revenuService;
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
rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe.getDoti()));
				//formation
rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe.getDoti()));
				//Punition
rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe.getDoti()));
				//prix
rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe.getDoti()));
				//moyen
rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe.getDoti())));
//moyen
rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));
rapportDeEvaluationService.save(rapportDeEvaluation);
TypeNotification typeNotification = notificationService.findByType("save");
NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,employe , new Date(), "save grade employe");
notificationEmployeService.save(notificationEmploye);
		return 1;
}
	}
public List<GradeEmploye> getGradeNonTraiteByType(String type){
	List<GradeEmploye> gradeEmployes = findGradeNonTraite();
	List<GradeEmploye> resultat = new ArrayList<GradeEmploye>();
	for (GradeEmploye gradeEmploye : gradeEmployes) {
		if(gradeEmploye.getGrade().getLibelle().equals(type)) {
			resultat.add(gradeEmploye);
		}
	}
	return resultat;
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
	if(noteGeneraleService.findNoteDeEmploye(employe.getDoti())!= null) {
		//note
		rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe.getDoti()));
		//moyen
rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe.getDoti())));
//moyen
rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));

	} if(formationService.findFormationDeEmploye(employe.getDoti())!= null) {
		//formation
		rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe.getDoti()));
	}	if(punitionEmployeService.findPunitionDeEmploye(employe.getDoti())!= null) {
		//punition
		rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe.getDoti()));
	}	if(prixEmployeService.findPrixDeEmploye(employe.getDoti())!= null) {
		//prix
		rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe.getDoti()));
	}
	employe.setDateProchainEvaluation(null);
	employeDao.save(employe);
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
	 if(rapportDeEvaluationService.findByNouveauGradeIdAndEmployeDoti(id, gradeEmploye.getDoti()).getId() != null) {
		 rapportDeEvaluationService.deleteById(rapportDeEvaluationService.findByNouveauGradeIdAndEmployeDoti(id, gradeEmploye.getDoti()).getId());
		 
	 }
		TypeNotification typeNotification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,employe , new Date(), "delete grade employe");
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
SalaireEmploye salaireEmploye = salaireEmployeService.findByEmployeDoti(employe.getDoti());
salaireEmploye.setSalaireNet(getSalaireParGrade(gradeEmploye.getGrade()));
if(employe.getEnfants() == null) {
	employe.setEnfants(0);
}
			//emoulument
Double som = 0.0;
salaireEmploye.setAllocationDeEncadrement(emolumentsService.findByLibelle("allocationDeEncadrement"));
som += salaireEmploye.getAllocationDeEncadrement().getMontant();
salaireEmploye.setAllocationDeEnseignement(emolumentsService.findByLibelle("allocationDeEnseignement"));
som += salaireEmploye.getAllocationDeEnseignement().getMontant();
salaireEmploye.setIdemDeLaResidence(emolumentsService.findByLibelle("idemDeLaResidence"));
som += salaireEmploye.getIdemDeLaResidence().getMontant();
salaireEmploye.setIdemFamialieleMarocaine(emolumentsService.findByLibelle("idemFamialieleMarocaine"));
som += salaireEmploye.getIdemFamialieleMarocaine().getMontant() * employe.getEnfants();
			//revenu
salaireEmploye.setAssuranceMaladieObligatoire(revenuService.findByLibelle("assuranceMaladieObligatoire"));
som -= salaireEmploye.getAssuranceMaladieObligatoire().getMontant();
salaireEmploye.setImpotSurLeRevenu(revenuService.findByLibelle("impotSurLeRevenu"));
som -= salaireEmploye.getImpotSurLeRevenu().getMontant();
salaireEmploye.setMutuelleCaisseRetraitEtDeces(revenuService.findByLibelle("mutuelleCaisseRetraitEtDeces"));
som -= salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant();
salaireEmploye.setCaisseMarocaineDeretrait(revenuService.findByLibelle("caisseMarocaineDeretrait"));
som -= salaireEmploye.getCaisseMarocaineDeretrait().getMontant();
salaireEmploye.setMonatntModifie(salaireEmploye.getSalaireNet() + som);
// save salaire employe
salaireEmployeService.save(salaireEmploye);
employeDao.save(employe);
	return 1;
}

public static Double getSalaireParGrade(Grade grade) {
	Double salaire = null;
	switch (grade.getLibelle()) {
	case "grade1":
		salaire = 4000.00;
		break;
	case "grade2":
		salaire = 4500.00;
		break;
	case "grade3":
		salaire = 5000.00;
		break;
	case "grade4":
		salaire = 5500.00;
		break;
	case "grade5":
		salaire = 6000.00;
		break;
	case "grade6":
		salaire = 6500.00;
		break;
	case "grade7":
		salaire = 7000.00;
		break;
	case "grade8":
		salaire = 7500.00;
		break;
	case "grade9":
		salaire = 8000.00;
		break;
	case "grade10":
		salaire = 8500.00;
		break;
	}
	return salaire;
}
@Override
public int update(GradeEmploye grade) {
	Employe employe = employeService.findByDoti(grade.getDoti());
	Grade grade2 = gradeService.findByLibelle(grade.getGrade().getLibelle());
	grade.setGrade(grade2);
	grade.setDoti(employe.getDoti());
	TypeNotification typeNotification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,employe , new Date(), "update grade employe");
	notificationEmployeService.save(notificationEmploye);
	// ncriw rapport evaluation
	gradeDao.save(grade);
	return 1;
}
public int listeDesGradesEmployesExcel(List<GradeEmploye> gradeEmployes) {
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Workbook workbook = new XSSFWorkbook();
	Employe employe = null;
	for (GradeEmploye gradeEmploye : gradeEmployes) {
		employe = employeDao.findByDoti(gradeEmploye.getDoti());
	}
	Sheet sheet = workbook.createSheet("Liste grades");
	Row header = sheet.createRow(0);
	header.createCell(0).setCellValue("grade");
	header.createCell(1).setCellValue("date Affecatation");


	int rowNum = 1;
	for (GradeEmploye gradeEmploye : gradeEmployes) {
		Row row = sheet.createRow(rowNum++);
		row.createCell(0).setCellValue(gradeEmploye.getGrade().getLibelle());
		row.createCell(1).setCellValue(simpleDateFormat.format(gradeEmploye.getDateDeAffectation()));
	}
	String fileLocation = "C:/Users/hp/Desktop/";
	try {
		FileOutputStream outputStream = new FileOutputStream(
				fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste grades.xlsx");
		workbook.write(outputStream);
		workbook.close();

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return 1;
}

public int listeDeGradeDeEmployePdf(List<GradeEmploye> grades) throws DocumentException, FileNotFoundException {
	String doti = null;
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String fileLocation = "C:/Users/hp/Desktop/";
	for (GradeEmploye gradeEmploye : grades) {
	doti = gradeEmploye.getDoti();
}
	Employe employe = employeService.findByDoti(doti);
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(fileLocation + employe.getFirstName() + employe.getLastName() + "listeDeGrade.pdf")); 
	
	document.open();
//	Image img,img1;
	//try {
		//img = Image.getInstance("fstgIcone.png");
		//img.setAlignment(Element.ALIGN_TOP);
	//	img.setAlignment(Element.ALIGN_LEFT);
	//	document.add(img);
	//} catch (MalformedURLException e) {
		//e.printStackTrace();
	//} catch (IOException e) {
		//e.printStackTrace();
	//}
	Font font1 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
	Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
	"Faculté des Sciences et Techniques"
					+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font1);
	p0.setAlignment(Element.ALIGN_LEFT);
	document.add(p0);
	Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	Paragraph p1 = new Paragraph("\n\t liste des grades de : " + employe.getFirstName() + employe.getLastName() + " \n\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

  
  PdfPTable table = new PdfPTable(2); // 3 columns.
table.setWidthPercentage(100);
  PdfPCell cell1 = new PdfPCell(new Paragraph("grade"));
  cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
  cell1.setBackgroundColor(BaseColor.GRAY);
  PdfPCell cell2 = new PdfPCell(new Paragraph("date Affectation"));
  cell2.setBackgroundColor(BaseColor.GRAY);
  cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(cell1);
  table.addCell(cell2);

	for (GradeEmploye gradeEmploye : grades) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(gradeEmploye.getGrade().getLibelle()));
	    cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell11 = new PdfPCell(new Paragraph(simpleDateFormat.format(gradeEmploye.getDateDeAffectation())));
	    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);

	    table.addCell(cell10);
	    table.addCell(cell11);
	}

document.add(table);
 Font f = new Font();
 f.setStyle(Font.BOLD);
 f.setSize(8);
 
 Paragraph p4 = new Paragraph( "\n  signer :",f);
 p4.setAlignment(Element.ALIGN_RIGHT);
 document.add(p4);

 Paragraph p20 = new Paragraph( "\n  marakech  le :"+ simpleDateFormat.format(new  Date()),f);
 p20.setAlignment(Element.ALIGN_RIGHT);
 document.add(p20);
  document.close();
	TypeNotification typeNotification = notificationService.findByType("imprimer");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,employe , new Date(), "imprimer liste grade employe");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}	
public List<Employe> getDateEvaluation() {
	List<Employe> employes = employeService.findAll();
	List<Employe> resultats = new ArrayList<Employe>();
	for (Employe employe : employes) {
		if(employe.getDateProchainEvaluation() != null) {
		if(DateUlils.verifierDateSup(employe.getDateProchainEvaluation(),new Date())) {
			if(employe.getDernierGrade().getGrade().getLibelle().equals("grade10") || employe.getDernierGrade().getGrade().getLibelle().equals("gradeExceptionnel")) {
				creeUnGradeNonTraite(employe.getDoti());
			}else {
			if(DateUlils.GetMention(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe.getDoti()))) != null) {
				employe.setDateAvancementPrevue(DateUlils.getDateAvancementnDeGrade(employe.getDernierGrade(), DateUlils.GetMention(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe.getDoti())))));
			}
				employe.setDateProchainEvaluation(null);
				employeDao.save(employe);
				NotificationEmploye notificationEmploye = new NotificationEmploye();
				notificationEmploye.setDateDeNotification(new Date());
				notificationEmploye.setEmploye(employe);
				notificationEmploye.setLibelle("avanement non traite est créé");
				notificationEmploye.setNotification(notificationService.findByType("avancement aujourd'hui"));
				resultats.add(employe);
		}
		}
		}
	}
	return resultats;
}
boolean disponible;
public List<Employe> getDateAvancement() {
	List<Employe> employes = employeService.findAll();
	List<Employe> resultats = new ArrayList<Employe>();
	List<NoteGeneralDeAnnee> notes = new ArrayList<NoteGeneralDeAnnee>();
	this.disponible = false;
	for (Employe employe : employes) {
		 disponible = false;
		if(employe.getDateAvancementPrevue() != null && !employe.getDernierGrade().getGrade().getLibelle().equals("grade10")) {
		if(DateUlils.verifierDateSup(employe.getDateAvancementPrevue(),new Date())) {
			GradeEmploye gradeEmploye = new GradeEmploye();
				gradeEmploye.setDoti(employe.getDoti());
				gradeEmploye.setGrade(gradeService.findByLibelle(DateUlils.getNouvauGrade(employe.getDernierGrade().getGrade())));
				gradeEmploye.setEtat("en traitement");
				gradeEmployeService.findGradeNonTraite().forEach(grade->{
					if(grade.getGrade().getLibelle() == gradeEmploye.getGrade().getLibelle() && grade.getDoti() == gradeEmploye.getDoti()) {
						this.disponible = true;
						}
				});
				if(this.disponible == false) {
				gradeDao.save(gradeEmploye);
				//rapport evaluation
				RapportDeEvaluation rapportDeEvaluation = new RapportDeEvaluation();
				rapportDeEvaluation.setEmploye(employe);
				rapportDeEvaluation.setNouveauGrade(gradeEmploye);
				if(noteGeneraleService.findNoteDeEmploye(employe.getDoti())!= null) {
					//note
					rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe.getDoti()));
					//moyen
					rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe.getDoti())));
					//moyen
					rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));}
					if(formationService.findFormationDeEmploye(employe.getDoti())!= null) {
					//formation
					rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe.getDoti()));}
					if(punitionEmployeService.findPunitionDeEmploye(employe.getDoti())!= null) {
					//punition
					rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe.getDoti()));}
					if(prixEmployeService.findPrixDeEmploye(employe.getDoti())!= null) {
					//prix
					rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe.getDoti()));}
					rapportDeEvaluationDao.save(rapportDeEvaluation);
					employe.setDateAvancementPrevue(null);
					employe.setDateProchainEvaluation(null);
					//employe.setDateProchainEvaluation(DateUlils.getDateEvaluationDeGrade(rapportDeEvaluation.getNouveauGrade()));
					employeDao.save(employe);
					NotificationEmploye notificationEmploye = new NotificationEmploye();
					notificationEmploye.setDateDeNotification(new Date());
					notificationEmploye.setEmploye(employe);
					notificationEmploye.setLibelle("avanement non traite est créé");
					notificationEmploye.setNotification(notificationService.findByType("avancement aujourd'hui"));
					resultats.add(employe);
		}
		}
		}
	}
	return resultats;
}

}
