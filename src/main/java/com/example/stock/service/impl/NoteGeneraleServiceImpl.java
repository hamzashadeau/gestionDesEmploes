package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.NoteGeneraleDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NoteService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
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
	noteGeneralDeAnnee.setFuulName(employe.getFullName());
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
	//calucler la mention et la moyen
	noteGeneraleDao.save(noteGeneralDeAnnee);
	employe.setDernierNote(noteGeneralDeAnnee);
	employeDao.save(employe);
	Notification notification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "save note");
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
		Notification notification = notificationService.findByType("delete");
		Employe employe =  employeDao.findByDoti(noteGeneralDeAnnee.getEmployeDoti());
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "delete note employe ");
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
public List<NoteGeneralDeAnnee> findNoteDeEmploye(Employe employe) {
	Employe employe2 = employeService.findByDoti(employe.getDoti());
	List<NoteGeneralDeAnnee> punitionEmployes = findByEmployeDoti(employe.getDoti());
	List<NoteGeneralDeAnnee> resultat = new ArrayList<NoteGeneralDeAnnee>();
	for (NoteGeneralDeAnnee punitionEmploye : punitionEmployes) {
		System.out.println("ha date hna" +  punitionEmploye.getDate());
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

//rapport des note
public int RapportDesNoteePdf(NoteGeneralDeAnnee note) throws DocumentException, FileNotFoundException {
	Employe employe = employeService.findByDoti(note.getEmployeDoti());
		Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream( note.getFuulName() + "RapportDesNote.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t rapport des notes\n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
	Paragraph p3 = new Paragraph("\n info de base", font);
	p3.setAlignment(Element.ALIGN_LEFT);
	document.add(p3);
	
    Paragraph p = new Paragraph();
    p.add("\n\n*FullName: " + note.getFuulName()+     "                                  *cin:" + employe.getCin() + " \n" + 
    		"*doti:" + employe.getDoti() + "                                                               *email:" + employe.getEmail() + "\n" + 
    		"*gender: " + employe.getGender() + "                                               *situation Familial:" + employe.getSituationFamiliale()+ " \n" + 
    "*adresse: " + employe.getAdresse() + "                                       *enfant: " + employe.getEnfants() + "\n" + 
    		"*date de naissance:" + employe.getDateDeNaissance() + "                                *lieu de naissance : " + employe.getLieuDeNaissance() + "\n" + 
    		"*departement :" + employe.getDep().getNom() + "                                             * fonction : " + employe.getFonction().getLibelle() + "\n" + 
    		"*date entrée : " + employe.getDateEntree() + "");
    document.add(p);
    
    //Note De Rentabilite
    Paragraph p4 = new Paragraph("\n Note De Rentabilite", font);
	p4.setAlignment(Element.ALIGN_LEFT);
	document.add(p4);
	
    Paragraph p5 = new Paragraph();
    p5.add("\n\n*remarque: " + note.getNoteDeRentabilite().getRemarque()+     "                                  *mention:" + note.getNoteDeRentabilite().getMention().toString() + " \n" 
    + "");
    document.add(p5);
    
    
    //Note De Affectation Des Taches Liee Au Travail
    Paragraph p6 = new Paragraph("\n Note De Affectation Des Taches Liee Au Travail", font);
	p6.setAlignment(Element.ALIGN_LEFT);
	document.add(p6);
	
    Paragraph p7 = new Paragraph();
    p7.add("\n\n*remarque: " + note.getNoteDeAffectationDesTachesLieeAuTravail().getRemarque()+     "                                  *mention:" + note.getNoteDeAffectationDesTachesLieeAuTravail().getMention().toString() + " \n" 
    + "");
    document.add(p7);

    //Note De Capacite De Organisation
    Paragraph p8 = new Paragraph("\n Note De Capacite De Organisation", font);
	p8.setAlignment(Element.ALIGN_LEFT);
	document.add(p8);
	
    Paragraph p9 = new Paragraph();
    p9.add("\n\n*remarque: " + note.getNoteDeCapaciteDeOrganisation().getRemarque()+     "                                  *mention:" + note.getNoteDeCapaciteDeOrganisation().getMention().toString() + " \n" 
    + "");
    document.add(p9);
    
    //Note De Compotement
    Paragraph p10 = new Paragraph("\n Note De Compotement", font);
	p10.setAlignment(Element.ALIGN_LEFT);
	document.add(p10);
	
    Paragraph p11 = new Paragraph();
    p11.add("\n\n*remarque: " + note.getNoteDeCompotement().getRemarque()+     "                                  *mention:" + note.getNoteDeCompotement().getMention().toString() + " \n" 
    + "");
    document.add(p11);
    
    //Note De Recherche Et De Innovation
    Paragraph p12 = new Paragraph("\n Note De Recherche Et De Innovation", font);
	p12.setAlignment(Element.ALIGN_LEFT);
	document.add(p12);
	
    Paragraph p13 = new Paragraph();
    p13.add("\n\n*remarque: " + note.getNoteDeRechercheEtDeInnovation().getRemarque()+     "                                  *mention:" + note.getNoteDeRechercheEtDeInnovation().getMention().toString() + " \n" 
    + "");
    document.add(p13);
   Font f = new Font();
   f.setStyle(Font.BOLD);
   f.setSize(8);
   
   Paragraph p2 = new Paragraph( "\n \r\r\r\r signer :",f);
   p2.setAlignment(Element.ALIGN_RIGHT);
   document.add(p2);

   Paragraph p20 = new Paragraph( "\n \r\r\r\r marakech  le :"+new  Date().toString(),f);
   p20.setAlignment(Element.ALIGN_LEFT);
   document.add(p20);
    document.close();
	Notification notification = notificationService.findByType("imprimer");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "imprimer rapport note");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}
}
