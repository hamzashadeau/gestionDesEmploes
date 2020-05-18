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

import com.example.stock.Dao.PrixEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.service.facade.PrixEmployeService;
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
public class PrixEmployeServiceImpl implements PrixEmployeService {
	
@Autowired
private PrixEmployeDao prixEmployeDao;


@Override
public int save(PrixEmploye prixEmploye) {
	if(findByid(prixEmploye.getId())!= null) {
return -1;
}else {
	prixEmployeDao.save(prixEmploye);
		return 1;
}
	}

@Override
public PrixEmploye findByid(Long id) {
	if (prixEmployeDao.findById(id).isPresent()) {
		return prixEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	prixEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<PrixEmploye> findAll() {
	return prixEmployeDao.findAll();
}

@Override
public List<PrixEmploye> findByPrixLibelle(String libelle) {
	return prixEmployeDao.findByPrixLibelle(libelle);
}
@Override
public List<PrixEmploye> findPrixDeEmploye(Employe employe) {
	List<PrixEmploye> punitionEmployes = findByEmployeDoti(employe.getDoti());
	List<PrixEmploye> resultat = new ArrayList<PrixEmploye>();
	for (PrixEmploye punitionEmploye : punitionEmployes) {
		if(DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(), punitionEmploye.getDateDeObtenation()))
			resultat.add(punitionEmploye);
	}
	return resultat;
}

@Override
public List<PrixEmploye> findByEmployeDoti(Integer doti) {
	return prixEmployeDao.findByEmployeDoti(doti);
}

public int listeDesPrixPdf(ArrayList<PrixEmploye> prixEmployes) throws DocumentException, FileNotFoundException {
	String fullName = null;
	for (PrixEmploye prixEmploye : prixEmployes) {
		fullName = prixEmploye.getEmploye().getFullName();
	}
		Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("listePrixEmploye.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des prix" + fullName + " \n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(3); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("prix"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("date Obtenation"));


    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);


    for (PrixEmploye prixEmploye : prixEmployes) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(prixEmploye.getEmploye().getFullName()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(prixEmploye.getPrix().getLibelle()));
	    PdfPCell cell12 = new PdfPCell(new Paragraph(prixEmploye.getDateDeObtenation().toString()));
	   
	    table.addCell(cell10);
	    table.addCell(cell11);
	    table.addCell(cell12);
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
