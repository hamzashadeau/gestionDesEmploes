package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.TypeDocumentDao;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.bean.TypeDocument;
import com.example.stock.service.facade.TypeDocumentService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {
@Autowired
private TypeDocumentDao typeDocumentDao;

//creeDocument 
public int creeDocument(String titre,String body) throws DocumentException, FileNotFoundException {
	
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf")); 
	
	document.open();
	Image img,img1;
	try {
		img = Image.getInstance("fstgIcone.png");
//		img1 = Image.getInstance("gouveronementEducation.jpg");
		img.setAlignment(Element.ALIGN_TOP);
		img.setAlignment(Element.ALIGN_LEFT);
		//img1.setAlignment(Element.ALIGN_TOP);
	//	img1.setAlignment(Element.ALIGN_RIGHT);
		document.add(img);
		//document.add(img1);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	Paragraph p1 = new Paragraph("\n\t " + titre , font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);
	

  Paragraph p = new Paragraph();
  p.add(" \r\n" + body);

  p.setAlignment(Element.ALIGN_LEFT);
  document.add(p);

  Paragraph p2 = new Paragraph();
  p2.add(" \n\r\rCETTE ATTESTATION LUI EST DELIVREE POUR SERVIR ET VALOIR CE QUE DE DROIT.\r\n" + " \n \r\r\r\r Signé ");//no alignment
  p2.setAlignment(Element.ALIGN_CENTER);
  document.add(p2);

  Font f = new Font();
  f.setStyle(Font.BOLD);
  f.setSize(8);

  Paragraph p4 = new Paragraph( "\n \r\r marakech  le :"+new  Date().toString(),f);
  p4.setAlignment(Element.ALIGN_LEFT);
  document.add(p4);

	document.close();
	return 1;
	}
@Override
public int save(TypeDocument typeDocument) {
	if(findByid(typeDocument.getId())!= null) {
return -1;
}else {
	typeDocumentDao.save(typeDocument);
		return 1;
}
	}

@Override
public TypeDocument findByid(Long id) {
	if (typeDocumentDao.findById(id).isPresent()) {
		return typeDocumentDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	typeDocumentDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public TypeDocument findByLibelle(String type) {
	return typeDocumentDao.findByLibelle(type);
}

@Override
public List<TypeDocument> findAll() {
	return typeDocumentDao.findAll();
}




}
