package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.CongeDao;
import com.example.stock.bean.Congé;
import com.example.stock.bean.Employe;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.CongeService;
import com.example.stock.service.facade.EmployeService;
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


@Override
public int save(Congé congé) {
	Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
	TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
	if(employe == null) {
		return -2;
	}else if(congé2 == null) {
		return -3;
	}else 
	if(congé.getId() != null) {
return -1;
}else {
	congé.setCongee(congé2);
	congé.setEmploye(employe);
	congeDao.save(congé);
		return 1;
}
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
	congé.setEmploye(employe);
	congeDao.save(congé);
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
	congeDao.deleteById(id);
	if (findByid(id) == null) {
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
public List<Congé> findByEmployeDoti(Integer doti) {
	return congeDao.findByEmployeDoti(doti);
}

//liste des conges
public int listeDesCongéPdf(List<Congé> conges) throws DocumentException, FileNotFoundException {
	String fullName = null;
	for (Congé congé : conges) {
		 fullName = congé.getEmploye().getFullName();
	}
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(fullName + "ListeCongé.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des conge de"+ fullName +" \n\r\n" , font);
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
	return 1;
}



}
