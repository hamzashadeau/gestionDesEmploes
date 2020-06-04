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

import com.example.stock.Dao.CongeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Congé;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.CongeService;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.TypeCongeeService;
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
public class CongeServiceImpl implements CongeService {
@Autowired
private CongeDao congeDao;
@Autowired
private EmployeService employeService;
@Autowired
private TypeCongeeService typeCongeeService;
@Autowired
private NotificationService notificationService;
@Autowired
private NotificationEmployeService notificationEmployeService;

@Override
public int save(Congé congé) {
	Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
	TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
	if(employe == null) {
		return -2;
	}else if(congé2 == null) {
		return -3;
	}else 	if(congé.getId() != null) {
return -1;
}else {
	if(congé.getCongee().getLibelle().equals("conge exceptionnel") && employe.getSoldeRestantesCongeExceptionnel() < congé.getPeriode()) {
		return -4;
	}else {
		employe.setSoldeRestantesCongeExceptionnel(employe.getSoldeRestantesCongeExceptionnel() - congé.getPeriode());
	}
	if(congé.getCongee().getLibelle().equals("certificat court duree") && congé.getPeriode() > 84) {
		// modifier salaire
	}
	congé.setCongee(congé2);
	congé.setEmploye(employe);
	congé.setDateDeFin(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()));
	congeDao.save(congé);
	Notification notification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "update conge");
	notificationEmployeService.save(notificationEmploye);
		return 1;
}
	}


@Override
public List<Congé>  findCongeByAnne(Integer annee, String type) {
	List<Congé> resultat = new ArrayList<Congé>();
	if(type.equals("longDuree")) {
	List<Congé> congés = findByCongeeLibelle("certificat long duree");
	for (Congé congé : congés) {
		if(DateUlils.verifierdateDebutEtFin(congé.getDateDeDebut(), annee))
			resultat.add(congé);
	}
	}
	else if(type.equals("courtDuree")) {
	List<Congé> congés = findByCongeeLibelle("certificat court duree");
	for (Congé congé : congés) {
		if(DateUlils.verifierdateDebutEtFin(congé.getDateDeDebut(), annee))
			resultat.add(congé);
	}
	}
return	resultat;
}

@Override
public int update(Congé congé) {
	Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
	TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
	if(employe == null) {
		return -2;
	}else if(congé2 == null) {
		return -3;
	}else {
	congé.setCongee(congé2);
	congé.setDateDeFin(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()));
	congé.setEmploye(employe);
	congeDao.save(congé);
	Notification notification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "update conge");
	notificationEmployeService.save(notificationEmploye);
		return 1;
}
	}

@Override
public Congé findByid(Long id) {
	if (congeDao.findById(id).isPresent()) {
		return congeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	Congé congé = findByid(id);
	congeDao.deleteById(id);
	if (findByid(id) == null) {
		Notification notification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification, congé.getEmploye(), new Date(), "delete conge");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	} else
		return -1;
}


@Override
public List<Congé> findAll() {
	return congeDao.findAll();
}

@Override
public List<Congé> findByCongeeLibelle(String libelle) {
	return congeDao.findByCongeeLibelle(libelle);
}

@Override
public List<Congé> findByEmployeEmail(String email) {
	return congeDao.findByEmployeEmail(email);
}

@Override
public List<Congé> findByEmployeDoti(String doti) {
	return congeDao.findByEmployeDoti(doti);
}

//liste des conges
public int listeDesCongéPdf(List<Congé> conges) throws DocumentException, FileNotFoundException {
	Employe employe = null;
	for (Congé congé : conges) {
		 employe = congé.getEmploye();
	}
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(employe.getFullName() + "ListeCongé.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des conge de"+ employe.getFullName() +" \n\r\n" , font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(4); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("typeDeCongé"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("dateDeDebut"));
    PdfPCell cell4 = new PdfPCell(new Paragraph("periode"));

    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);

	for (Congé congé : conges) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(congé.getEmploye().getFullName()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(congé.getCongee().getLibelle()));
	    PdfPCell cell12 = new PdfPCell(new Paragraph(congé.getDateDeDebut().toString()));
	    PdfPCell cell13 = new PdfPCell(new Paragraph(congé.getPeriode().toString()));
		
	    table.addCell(cell10);
	    table.addCell(cell11);
	    table.addCell(cell12);
	    table.addCell(cell13);
	
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
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "imprimer liste conge");
	notificationEmployeService.save(notificationEmploye);
    return 1;
}

public List<Congé> findCongeCertificat() {
	List<Congé> congés = findByCongeeLibelle("certificat court duree 3 mois");
	 congés.addAll(findByCongeeLibelle("certificat long duree")) ;
	 congés.addAll(findByCongeeLibelle("certificat court duree 6 mois"));
	 return congés;
}
public List<Congé> findListeCertificatByAnnee(){
	List<Congé> congés = findCongeCertificat();
	List<Congé> resultat = new ArrayList<Congé>();
	for (Congé congé : congés) {
		System.out.println("ha ana");
		if(DateUlils.VerifieDateSup(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()))) {
			resultat.add(congé);
		}
	}
	return resultat;
}


@Override
public List<Congé> findByCongeeLibelleAndDateDeDebut(String libelle, Date date) {
	return congeDao.findByCongeeLibelleAndDateDeDebut(libelle, date);
}


@Override
public List<Congé> findByEmployeDotiAndCongeeLibelle(String matricule, String libelle) {
	return congeDao.findByEmployeDotiAndCongeeLibelle(matricule, libelle);
}



}
