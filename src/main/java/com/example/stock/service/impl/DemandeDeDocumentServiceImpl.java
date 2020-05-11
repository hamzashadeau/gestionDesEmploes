package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DemaneDeDocumentDao;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.service.facade.DemandeDeDocumentService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class DemandeDeDocumentServiceImpl implements DemandeDeDocumentService {
@Autowired
private DemaneDeDocumentDao demaneDeDocumentDao;


@Override
public int save(DemaneDeDocument demaneDeDocument) {
	if(findByid(demaneDeDocument.getId())!= null) {
return -1;
}else {
	demaneDeDocumentDao.save(demaneDeDocument);
		return 1;
}
	}


public String hellowordl() throws DocumentException, FileNotFoundException {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
	 
	document.open();
	Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	Chunk chunk = new Chunk("ATTESTATION DE TRAVAIL", font);
	document.add(chunk);

    Paragraph p = new Paragraph();
    p.add("\n\n\n\n  Je suis Mr X X X titre de girant de la soc PERFECT STEEL S.A.R.L sise a 20. Rue 144 an chokolay abdlah Casablanca, atteste par la présente que M. XXXXXXXxxx titulare de la carte\r\n" + 
    		"CIN N BHXOOXXXX travailler PERFECT STEEL en qual de Technin depuis le 04 aillet 2008 a ur.");
    p.setAlignment(Element.ALIGN_CENTER);

    document.add(p);

    Paragraph p2 = new Paragraph();
    p2.add(" \n\\n\\n\\n\\nCette attestation est delevrie pour servir et valoir ce que de droit\r\n");//no alignment
    p2.setAlignment(Element.ALIGN_CENTER);

    document.add(p2);

    Font f = new Font();
    f.setStyle(Font.BOLD);
    f.setSize(8);

    document.add(new Paragraph(new  Date().toString(), f));
    //p3.setAlignment(Element.ALIGN_CENTER);

	document.close();
	return "done";
}
@Override
public DemaneDeDocument findByid(Long id) {
	if (demaneDeDocumentDao.findById(id).isPresent()) {
		return demaneDeDocumentDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	demaneDeDocumentDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<DemaneDeDocument> findAll() {
	return demaneDeDocumentDao.findAll();
}

@Override
public List<DemaneDeDocument> findByEmployeId(Long id) {
	return demaneDeDocumentDao.findByEmployeId(id);
}

@Override
public List<DemaneDeDocument> findByEmployeEmail(String email) {
	return demaneDeDocumentDao.findByEmployeEmail(email);
}

@Override
public List<DemaneDeDocument> findByEmployeDoti(Integer doti) {
	return demaneDeDocumentDao.findByEmployeDoti(doti);
}

@Override
public List<DemaneDeDocument> findByEtat(String etat) {
	return demaneDeDocumentDao.findByEtat(etat);
}



}
