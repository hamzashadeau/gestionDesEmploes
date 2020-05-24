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

import com.example.stock.Dao.FormationDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.FormationService;
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


@Override
public int save(Formation formation) {
	Employe employe = employeService.findByDoti(formation.getEmploye().getDoti());
	if(formation.getId()!= null) {
return -1;
}else if (employe == null) {
	return -2;
}{
	formation.setEmploye(employe);
	formationDao.save(formation);
		return 1;
}
	}
@Override
public int update(Formation formation) {
	Employe employe = employeService.findByDoti(formation.getEmploye().getDoti());
	if(formation.getId()== null) {
return -1;
}else if (employe == null) {
	return -2;
}{
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
	formationDao.deleteById(id);
	if (findByid(id) == null) {
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
public List<Formation> findByemployeDoti(Integer doti) {
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
	String fullName = null;
	for (Formation formation : formations) {
		fullName = formation.getEmploye().getFullName();
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
	Paragraph p1 = new Paragraph("\n\t liste des formation" + fullName + " \n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(7); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
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
	    PdfPCell cell10 = new PdfPCell(new Paragraph(formation.getEmploye().getFullName()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(formation.getAttestation()));
	    PdfPCell cell12 = new PdfPCell(new Paragraph(formation.getDomaine()));
	    PdfPCell cell13 = new PdfPCell(new Paragraph(formation.getEtablissement()));
	    PdfPCell cell14 = new PdfPCell(new Paragraph(formation.getMention().toString()));
	    PdfPCell cell15 = new PdfPCell(new Paragraph(formation.getVille()));
	    PdfPCell cell16 = new PdfPCell(new Paragraph(formation.getAnnee().toString()));
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
	return 1;
}

}
