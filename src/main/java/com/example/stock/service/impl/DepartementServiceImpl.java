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

import com.example.stock.Dao.DepartementDao;
import com.example.stock.bean.DepFonction;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.service.facade.DepFonctionService;
import com.example.stock.service.facade.DepartementService;
import com.example.stock.service.facade.EmployeService;
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
public class DepartementServiceImpl implements DepartementService {
@Autowired
private DepartementDao departementDao;
@Autowired
private DepartementService departementService;
@Autowired
private EmployeService employeService;
@Autowired
private DepFonctionService depFonctionService;
@Autowired
private NotificationService notificationService;
@Autowired
private NotificationEmployeService notificationEmployeService;

@Override
public int save(Departement departement) {
	Employe employe = employeService.findByDoti(departement.getChefdoti());
	if(employe == null) {
		return -2;
	} else if (departement.getId() != null) {
		return -3;
	} else {
	departement.setFullname(employe.getFirstName() + employe.getLastName());
	departement.setChefdoti(employe.getDoti());
	departementDao.save(departement);
	Notification notification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "save departement");
	notificationEmployeService.save(notificationEmploye);
		return 1;
	}
}
@Override
public int update(Departement departement) {
	Employe employe = employeService.findByDoti(departement.getChefdoti());
	if(employe == null) {
		return -2;
	} else if (departement.getId() == null) {
		return -3;
	} else {
	departement.setChefdoti(employe.getDoti());
	departementDao.save(departement);
	Notification notification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "update departement");
	notificationEmployeService.save(notificationEmploye);
		return 1;
	}
}

//liste Des departementse Pdf
public int listedepartementPdf() throws DocumentException, FileNotFoundException {
List<Departement> departements = findAll();
Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("listeDepartements.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des departements  \n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(2); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("nom"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("chef"));

    table.addCell(cell1);
    table.addCell(cell2);
    Employe employe = new Employe();
    for (Departement departement : departements) {
    	employe = employeService.findByDoti(departement.getChefdoti());
	    PdfPCell cell10 = new PdfPCell(new Paragraph(departement.getNom()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(departement.getChefdoti()));
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
	NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "imprimer liste des departement");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}	


@Override
public Departement findByid(Long id) {
	if (departementDao.findById(id).isPresent()) {
		return departementDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	List<Employe> employes = employeService.findByDepNom(departementService.findByid(id).getNom());
	if(employes != null) {
		return -2;
	}
//	for (Employe employe : employes) {
	//	employeService.deleteById(employe.getId());
	//}
	List<DepFonction> depFonctions = depFonctionService.findByDepartemantNom(departementService.findByid(id).getNom());
	//for (DepFonction depFonction : depFonctions) {
		//depFonctionService.deleteById(depFonction.getId());
	//}
	if(depFonctions != null) {
		return -3;
	} else {
		Notification notification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification,null , new Date(), "delete departemnt");
		notificationEmployeService.save(notificationEmploye);
	departementDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}
}

@Override
public Departement findByNom(String nom){
	return departementDao.findByNom(nom);
}


@Override
public Departement findByChefdoti(String doti) {
	return departementDao.findByChefdoti(doti);
}

@Override
public List<Departement> findAll() {
	return departementDao.findAll();
}

@Override
public int nombreDesDepartements() {
	return findAll().size();
}

}
