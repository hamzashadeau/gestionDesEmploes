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
import com.example.stock.Dao.NoteGeneraleDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NoteService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
public class NoteGeneraleServiceImpl implements NoteGeneraleService {
@Autowired
private NoteGeneraleDao noteGeneraleDao;
@Autowired
private NoteService noteService;
@Autowired
private EmployeService employeService;
@Autowired
private EmployeDao employeDao;
@Autowired
private NotificationService notificationService;
@Autowired
private NotificationEmployeService notificationEmployeService;

@Override
public int save(NoteGeneralDeAnnee noteGeneralDeAnnee) {
//	if(findByid(noteGeneralDeAnnee.getId())!= null) {
//return -1;
//}else {
	Double somme = 0.0;
	Employe employe = employeService.findByDoti(noteGeneralDeAnnee.getEmployeDoti());
	noteGeneralDeAnnee.setFuulName(employe.getFirstName() +" "+ employe.getLastName());
	//noteGeneralDeAnnee.getNoteDeAffectationDesTachesLieeAuTravail().setLibelle("NoteDeAffectationDesTachesLieeAuTravail"+ employe.getDoti() + noteGeneralDeAnnee.getDate().toString() );	
	noteService.save(noteGeneralDeAnnee.getNoteDeAffectationDesTachesLieeAuTravail());
	somme += noteGeneralDeAnnee.getNoteDeAffectationDesTachesLieeAuTravail().getMention();
	noteService.save(noteGeneralDeAnnee.getNoteDeCapaciteDeOrganisation());
	somme += noteGeneralDeAnnee.getNoteDeCapaciteDeOrganisation().getMention();
	noteService.save(noteGeneralDeAnnee.getNoteDeCompotement());
	somme += noteGeneralDeAnnee.getNoteDeCompotement().getMention();
	noteService.save(noteGeneralDeAnnee.getNoteDeRechercheEtDeInnovation());
	somme += noteGeneralDeAnnee.getNoteDeRechercheEtDeInnovation().getMention();
	noteService.save(noteGeneralDeAnnee.getNoteDeRentabilite());
	somme += noteGeneralDeAnnee.getNoteDeRentabilite().getMention();
	noteGeneralDeAnnee.setMoyenGeneral(somme/5);
	noteGeneralDeAnnee.setMention(DateUlils.GetMention(noteGeneralDeAnnee.getMoyenGeneral()));
	noteGeneraleDao.save(noteGeneralDeAnnee);
	employe.setDernierNote(noteGeneralDeAnnee);
	employe.setDateDeProchainNote(DateUlils.getDateDeNote(noteGeneralDeAnnee.getDate()));
	employeDao.save(employe);
	TypeNotification typeNotification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "save note");
	notificationEmployeService.save(notificationEmploye);
		return 1;
}
	//}

@Override
public NoteGeneralDeAnnee findByid(Long id) {
	if (noteGeneraleDao.findById(id).isPresent()) {
		return noteGeneraleDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	NoteGeneralDeAnnee noteGeneralDeAnnee = findByid(id);
	noteGeneraleDao.deleteById(id);
	if (findByid(id) == null) {
		TypeNotification typeNotification = notificationService.findByType("delete");
		Employe employe =  employeDao.findByDoti(noteGeneralDeAnnee.getEmployeDoti());
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "delete note employe ");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	} else
		return -1;
}


@Override
public List<NoteGeneralDeAnnee> findAll() {
	return noteGeneraleDao.findAll();
}

@Override
public List<NoteGeneralDeAnnee> findByEmployeDoti(String doti) {
	return noteGeneraleDao.findByEmployeDoti(doti);
}

@Override
public List<NoteGeneralDeAnnee> findNoteDeEmploye(String doti) {
	Employe employe = employeService.findByDoti(doti);
	List<NoteGeneralDeAnnee> punitionEmployes = findByEmployeDoti(employe.getDoti());
	List<NoteGeneralDeAnnee> resultat = new ArrayList<NoteGeneralDeAnnee>();
	for (NoteGeneralDeAnnee punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getDate()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

@Override
public NoteGeneralDeAnnee findByDateAndEmployeDoti(Date date, String doti) {
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//String strDate1 = formatter.format(date);
//Date date1 = formatter.parse(strDate1);
	//System.out.println(date1);
	return noteGeneraleDao.findByDateAndEmployeDoti(date, doti);
}

@Override
public List<NoteGeneralDeAnnee> findByEtat(String etat) {
	return noteGeneraleDao.findByEtat(etat);
}

@Override
public List<NoteGeneralDeAnnee> findNoteNonTraite(String etat) {
	return findByEtat("non traite");
}
public int listeDeEmployeAyantBesoinDUneNoteExcel(List<Employe> employes) {
	String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Workbook workbook = new XSSFWorkbook();
      Sheet sheet = workbook.createSheet("Liste employes");
	Row header = sheet.createRow(0);
      header.createCell(0).setCellValue("Cin");
      header.createCell(1).setCellValue("Numero");
      header.createCell(2).setCellValue("Prenom");
      header.createCell(3).setCellValue("Nom");
      header.createCell(4).setCellValue("Date Note Prévue");

      int rowNum = 1;
  	for (Employe employe : employes) {
         Row row = sheet.createRow(rowNum++);
         row.createCell(0).setCellValue(employe.getCin());
         row.createCell(1).setCellValue(employe.getDoti());
         row.createCell(2).setCellValue(employe.getFirstName());
         row.createCell(3).setCellValue(employe.getLastName());
         row.createCell(4).setCellValue(simpleDateFormat.format(employe.getDateDeProchainNote()));
         
	}
     String fileLocation = "C:/Users/hp/Desktop/";
     try {
	     FileOutputStream outputStream = new FileOutputStream(fileLocation + "Liste employes ayant besoin une note.xlsx");
		workbook.write(outputStream);
	     workbook.close();

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     return 1;
}
public int listeDeEmployeAyantBesoinDUneNotePDF(List<Employe> employes ) throws FileNotFoundException, DocumentException {
	Document document = new Document();
     String fileLocation = "C:/Users/hp/Desktop/";
PdfWriter.getInstance(document, new FileOutputStream(fileLocation + "listeEmployesAyantbesoindUneNote.pdf")); 
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

Font font = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.BLACK);
Paragraph p1 = new Paragraph("\n\t liste des Employes Ayant besoin Une Note: \n\r\n", font);
p1.setAlignment(Element.ALIGN_CENTER);
document.add(p1);
document.add(Chunk.NEWLINE);

PdfPTable table = new PdfPTable(new float[] { 15,15,15,15, 40}); // 3 columns.
table.setWidthPercentage(100);
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

PdfPCell cell1 = new PdfPCell(new Paragraph("Cin"));
cell1.setBackgroundColor(BaseColor.GRAY);
cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell2 = new PdfPCell(new Paragraph("Numero"));
cell2.setBackgroundColor(BaseColor.GRAY);
cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell3 = new PdfPCell(new Paragraph("Prenom"));
cell3.setBackgroundColor(BaseColor.GRAY);
cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell4 = new PdfPCell(new Paragraph("Nom"));
cell4.setBackgroundColor(BaseColor.GRAY);
cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell5 = new PdfPCell(new Paragraph("Date Note Prévue"));
cell5.setBackgroundColor(BaseColor.GRAY);
cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(cell1);
table.addCell(cell2);
table.addCell(cell3);
table.addCell(cell4);
table.addCell(cell5);
for (Employe employe : employes) {
PdfPCell cell10 = new PdfPCell(new Paragraph(employe.getCin().toString()));
cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell11 = new PdfPCell(new Paragraph(employe.getDoti().toString()));
cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell14 = new PdfPCell(new Paragraph(employe.getFirstName()));
cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell15 = new PdfPCell(new Paragraph(employe.getLastName()));
cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell13 = new PdfPCell(new Paragraph(simpleDateFormat.format(employe.getDateDeProchainNote())));
cell13.setHorizontalAlignment(Element.ALIGN_CENTER);

table.addCell(cell10);
table.addCell(cell11);
table.addCell(cell14);
table.addCell(cell15);
table.addCell(cell13);
}

document.add(table);

Font f = new Font();
f.setStyle(Font.BOLD);
f.setSize(8);

Paragraph p4 = new Paragraph( "\n \r\r\r\r signer :",f);
p4.setAlignment(Element.ALIGN_RIGHT);
document.add(p4);
document.add(Chunk.NEWLINE);
Paragraph p20 = new Paragraph( "\n \r\r\r\r marakech  le :"+new  Date().toString(),f);
p20.setAlignment(Element.ALIGN_LEFT);
document.add(p20);

document.close();
TypeNotification typeNotification = notificationService.findByType("imprimer");
NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,null , new Date(), "imprimer la liste employe");
notificationEmployeService.save(notificationEmploye);
return 1;
}
public int RapportDesNoteePdf(NoteGeneralDeAnnee note) throws DocumentException, FileNotFoundException {
	String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Employe employe = employeService.findByDoti(note.getEmployeDoti());
		Document document = new Document();
	    String fileLocation = "C:/Users/hp/Desktop/";
	PdfWriter.getInstance(document, new FileOutputStream(fileLocation +  note.getFuulName() + "RapportDesNote.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t rapport des notes de :  " + employe.getFirstName() +"  "+ employe.getLastName() + "\n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    //Note De Rentabilite
    Paragraph p4 = new Paragraph("\n Note De Rentabilite", font);
	p4.setAlignment(Element.ALIGN_CENTER);
	document.add(p4);
	
    Paragraph p5 = new Paragraph();
    p5.add("\n*remarque: " + note.getNoteDeRentabilite().getRemarque()+     "                                  *mention:" + note.getNoteDeRentabilite().getMention().toString() + " \n" 
    + "");
    document.add(p5);
    
    
    //Note De Affectation Des Taches Liee Au Travail
    Paragraph p6 = new Paragraph("\n  Note De Affectation Des Taches Liee Au Travail", font);
	p6.setAlignment(Element.ALIGN_CENTER);
	document.add(p6);
	
    Paragraph p7 = new Paragraph();
    p7.add("\n*remarque: " + note.getNoteDeAffectationDesTachesLieeAuTravail().getRemarque()+     "                                  *mention:" + note.getNoteDeAffectationDesTachesLieeAuTravail().getMention().toString() + " \n" 
    + "");
    document.add(p7);

    //Note De Capacite De Organisation
    Paragraph p8 = new Paragraph("\n   Note De Capacite De Organisation", font);
	p8.setAlignment(Element.ALIGN_CENTER);
	document.add(p8);
	
    Paragraph p9 = new Paragraph();
    p9.add("\n*remarque: " + note.getNoteDeCapaciteDeOrganisation().getRemarque()+     "                                  *mention:" + note.getNoteDeCapaciteDeOrganisation().getMention().toString() + " \n" 
    + "");
    document.add(p9);
    
    //Note De Compotement
    Paragraph p10 = new Paragraph("\n Note De Compotement", font);
	p10.setAlignment(Element.ALIGN_CENTER);
	document.add(p10);
	
    Paragraph p11 = new Paragraph();
    p11.add("\n*remarque: " + note.getNoteDeCompotement().getRemarque()+     "                                  *mention:" + note.getNoteDeCompotement().getMention().toString() + " \n" 
    + "");
    document.add(p11);
    
    //Note De Recherche Et De Innovation
    Paragraph p12 = new Paragraph("\n Note De Recherche Et De Innovation", font);
	p12.setAlignment(Element.ALIGN_CENTER);
	document.add(p12);
	
    Paragraph p13 = new Paragraph();
    p13.add("\n*remarque: " + note.getNoteDeRechercheEtDeInnovation().getRemarque()+     "                                  *mention:" + note.getNoteDeRechercheEtDeInnovation().getMention().toString() + " \n" 
    + "");
    document.add(p13);
   Font f = new Font();
   f.setStyle(Font.BOLD);
   f.setSize(8);
   
   Paragraph p2 = new Paragraph( "\n \r signer :",f);
   p2.setAlignment(Element.ALIGN_RIGHT);
   document.add(p2);

   Paragraph p20 = new Paragraph( "\n \r marakech  le :"+simpleDateFormat.format(new  Date()),f);
   p20.setAlignment(Element.ALIGN_RIGHT);
   document.add(p20);
    document.close();
	TypeNotification typeNotification = notificationService.findByType("imprimer");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "imprimer rapport note");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}
}
