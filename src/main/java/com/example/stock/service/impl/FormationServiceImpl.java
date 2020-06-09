package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.FormationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.FormationService;
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
public class FormationServiceImpl implements FormationService {
@Autowired
private FormationDao formationDao;
@Autowired
private EmployeService employeService;
@Autowired
private NotificationService notificationService;
@Autowired
private NotificationEmployeService notificationEmployeService;


@Override
public int save(Formation formation) {
	Employe employe = employeService.findByDoti(formation.getEmploye().getDoti());
	if(formation.getId() != null) {
return -1;
}else if (employe == null) {
	return -2;
}{
	Notification notification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "save formation");
	notificationEmployeService.save(notificationEmploye);
	formation.setEmploye(employe);
	formationDao.save(formation);
		return 1;
}
	}
@Override
public int update(Formation formation) {
	Employe employe = employeService.findByDoti(formation.getEmploye().getDoti());
	if(formation.getId() == null) {
return -1;
}else if (employe == null) {
	return -2;
}{
	Notification notification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "update formation");
	notificationEmployeService.save(notificationEmploye);
	formation.setEmploye(employe);
	formationDao.save(formation);
		return 1;
}
	}

@Override
public Formation findByid(Long id) {
	if (formationDao.findById(id).isPresent()) {
		return formationDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	Formation formation = findByid(id);
	formationDao.deleteById(id);
	if (findByid(id) == null) {
		Notification notification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification, formation.getEmploye(), new Date(), "delete formation");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	} else
		return -1;
}


@Override
public List<Formation> findAll() {
	return formationDao.findAll();
}

@Override
public List<Formation> findByemployeId(Long id) {
	return formationDao.findByEmployeId(id);
}

@Override
public List<Formation> findByemployeEmail(String email) {
	return formationDao.findByEmployeEmail(email);
}

@Override
public List<Formation> findByemployeDoti(String doti) {
	return formationDao.findByEmployeDoti(doti);
}
@Override
public List<Formation> findFormationDeEmploye(Employe employe) {
	List<Formation> punitionEmployes = findByemployeDoti(employe.getDoti());
	List<Formation> resultat = new ArrayList<Formation>();
	for (Formation punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getAnnee()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

public int listeDesFormationsPdf(ArrayList<Formation> formations) throws DocumentException, FileNotFoundException {
	 String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Employe employe = null;
	for (Formation formation : formations) {
		employe = formation.getEmploye();
	}
		Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("listeFormations.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des formation" + employe.getFirstName() + employe.getLastName() + " \n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(7); // 3 columns.
    table.setWidthPercentage(100);
    //table.setTotalWidth(600);
    //table.setLockedWidth(true);
    PdfPCell cell1 = new PdfPCell(new Paragraph("doti"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("attestation"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("domaine"));
    PdfPCell cell4 = new PdfPCell(new Paragraph("etablissement"));
    PdfPCell cell5 = new PdfPCell(new Paragraph("mention"));
    PdfPCell cell6 = new PdfPCell(new Paragraph("ville"));
    PdfPCell cell7 = new PdfPCell(new Paragraph("date"));

    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);
    table.addCell(cell7);

    for (Formation formation : formations) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(formation.getEmploye().getDoti()));
	    cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell11 = new PdfPCell(new Paragraph(formation.getAttestation()));
	    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell12 = new PdfPCell(new Paragraph(formation.getDomaine()));
	    cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell13 = new PdfPCell(new Paragraph(formation.getEtablissement()));
	    cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell14 = new PdfPCell(new Paragraph(formation.getMention().toString()));
	    cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell15 = new PdfPCell(new Paragraph(formation.getVille()));
	    cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
	    PdfPCell cell16 = new PdfPCell(new Paragraph(simpleDateFormat.format(formation.getAnnee())));
	    cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    table.addCell(cell10);
	    table.addCell(cell11);
	    table.addCell(cell12);
	    table.addCell(cell13);
	    table.addCell(cell14);
	    table.addCell(cell15);
	    table.addCell(cell16);
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
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "imprimer formation");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}

}
