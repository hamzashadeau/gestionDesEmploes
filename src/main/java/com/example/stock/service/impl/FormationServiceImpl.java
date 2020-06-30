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

import com.example.stock.Dao.FormationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.TypeNotification;
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
	TypeNotification typeNotification = notificationService.findByType("save");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "save formation");
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
	TypeNotification typeNotification = notificationService.findByType("update");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "update formation");
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
	Employe employe = formation.getEmploye();
	formationDao.deleteById(id);
	if (findByid(id) == null) {
		TypeNotification typeNotification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "delete formation");
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
public List<Formation> findFormationDeEmploye(String doti) {
	Employe employe = employeService.findByDoti(doti);
	List<Formation> punitionEmployes = findByemployeDoti(employe.getDoti());
	List<Formation> resultat = new ArrayList<Formation>();
	for (Formation punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getAnnee()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}
public int listeDesFormationsExcel(List<Formation> formations) {
	String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Workbook workbook = new XSSFWorkbook();
	Employe employe = null;
	for (Formation formation : formations) {
		employe = formation.getEmploye();
	}
      Sheet sheet = workbook.createSheet("Liste formations");
	Row header = sheet.createRow(0);
      header.createCell(0).setCellValue("Nom Complet");
      header.createCell(1).setCellValue("Attestation");
      header.createCell(2).setCellValue("Domaine");
      header.createCell(3).setCellValue("Etablissement");
      header.createCell(4).setCellValue("Mention");
      header.createCell(5).setCellValue("Ville");
      header.createCell(6).setCellValue("Date de formation");

      int rowNum = 1;
  	for (Formation formation : formations) {
         Row row = sheet.createRow(rowNum++);
         row.createCell(0).setCellValue(formation.getEmploye().getFirstName() +"  "+ formation.getEmploye().getLastName());
         row.createCell(1).setCellValue(formation.getAttestation());
         row.createCell(2).setCellValue(formation.getDomaine());
         row.createCell(3).setCellValue(formation.getEtablissement());
         row.createCell(4).setCellValue(formation.getMention());
         row.createCell(5).setCellValue(formation.getVille());
         row.createCell(6).setCellValue(simpleDateFormat.format(formation.getAnnee()));
         
	}
     String fileLocation = "C:/Users/hp/Desktop/";
     try {
	     FileOutputStream outputStream = new FileOutputStream(fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste formations.xlsx");
		workbook.write(outputStream);
	     workbook.close();

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     return 1;
}

public int listeDesFormationsPdf(ArrayList<Formation> formations) throws DocumentException, FileNotFoundException {
	 String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
     String fileLocation = "C:/Users/hp/Desktop/";
	Employe employe = null;
	for (Formation formation : formations) {
		employe = formation.getEmploye();
	}
		Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(fileLocation + employe.getFirstName() + " " + employe.getLastName() +"listeFormations.pdf")); 
	
	document.open();
	Font font1 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
	Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
	"Faculté des Sciences et Techniques"
					+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font1);
	p0.setAlignment(Element.ALIGN_LEFT);
	document.add(p0);
	Font font2 = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
	Paragraph p1 = new Paragraph("\n\t liste des formation :" + employe.getFirstName() + " " + employe.getLastName() + " \n\r\n", font2);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(new float[] { 20,20,20,15,10,15}); // 3 columns.
    table.setWidthPercentage(100);
    //table.setTotalWidth(600);
    //table.setLockedWidth(true);

    PdfPCell cell2 = new PdfPCell(new Paragraph("Attestation"));
    cell2.setBackgroundColor(BaseColor.GRAY);
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell3 = new PdfPCell(new Paragraph("Domaine"));
    cell3.setBackgroundColor(BaseColor.GRAY);
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell4 = new PdfPCell(new Paragraph("Etablissement"));
    cell4.setBackgroundColor(BaseColor.GRAY);
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell5 = new PdfPCell(new Paragraph("Mention"));
    cell5.setBackgroundColor(BaseColor.GRAY);
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell6 = new PdfPCell(new Paragraph("Ville"));
    cell6.setBackgroundColor(BaseColor.GRAY);
    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
    PdfPCell cell7 = new PdfPCell(new Paragraph("Date"));
    cell7.setBackgroundColor(BaseColor.GRAY);
    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);
    table.addCell(cell7);

    for (Formation formation : formations) {
	    PdfPCell cell11 = new PdfPCell(new Paragraph(formation.getAttestation()));
	    PdfPCell cell12 = new PdfPCell(new Paragraph(formation.getDomaine()));
	    PdfPCell cell13 = new PdfPCell(new Paragraph(formation.getEtablissement()));
	    PdfPCell cell14 = new PdfPCell(new Paragraph(formation.getMention().toString()));
	    PdfPCell cell15 = new PdfPCell(new Paragraph(formation.getVille()));
	    PdfPCell cell16 = new PdfPCell(new Paragraph(simpleDateFormat.format(formation.getAnnee())));
	    
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
	TypeNotification typeNotification = notificationService.findByType("imprimer");
	NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "imprimer formation");
	notificationEmployeService.save(notificationEmploye);
	return 1;
}

}
