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

import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.PrixEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.Prix;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.PrixEmployeService;
import com.example.stock.service.facade.PrixService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
	@Autowired
	private PrixService prixService;
	@Autowired
	private EmployeService employeService;
	@Autowired
	private NotificationEmployeService notificationEmployeService;
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public int save(PrixEmploye prixEmploye) {
		Employe employe = employeService.findByDoti(prixEmploye.getEmploye().getDoti());
		Prix prix = prixService.findByLibelle(prixEmploye.getPrix().getLibelle());
		if (prixEmploye.getId() != null) {
			return -1;
		} else if (prixEmploye.getEmploye() == null) {
			return -2;
		} else if (prixEmploye.getPrix() == null) {
			return -3;
		} else {
			prixEmploye.setEmploye(employe);
			prixEmploye.setPrix(prix);
			prixEmployeDao.save(prixEmploye);
			TypeNotification typeNotification = notificationService.findByType("save");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "save prix employe");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}
	}
	@Override
	public int update(PrixEmploye prixEmploye) {
		Employe employe = employeService.findByDoti(prixEmploye.getEmploye().getDoti());
		Prix prix = prixService.findByLibelle(prixEmploye.getPrix().getLibelle());
		if (prixEmploye.getId() != null) {
			return -1;
		} else if (prixEmploye.getEmploye() == null) {
			return -2;
		} else if (prixEmploye.getPrix() == null) {
			return -3;
		} else {
			prixEmploye.setEmploye(employe);
			prixEmploye.setPrix(prix);
			prixEmployeDao.save(prixEmploye);
			TypeNotification typeNotification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "update prix employe");
			notificationEmployeService.save(notificationEmploye);
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
		PrixEmploye prixEmploye = findByid(id);
		Employe employe = prixEmploye.getEmploye();
		TypeNotification typeNotification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "delete prix employe");
		notificationEmployeService.save(notificationEmploye);
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
	public List<PrixEmploye> findPrixDeEmploye(String doti) {
		Employe employe = employeService.findByDoti(doti);
		List<PrixEmploye> punitionEmployes = findByEmployeDoti(employe.getDoti());
		List<PrixEmploye> resultat = new ArrayList<PrixEmploye>();
		for (PrixEmploye punitionEmploye : punitionEmployes) {
			if (DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(),
					punitionEmploye.getDateDeObtenation()))
				resultat.add(punitionEmploye);
		}
		return resultat;
	}

	@Override
	public List<PrixEmploye> findByEmployeDoti(String doti) {
		return prixEmployeDao.findByEmployeDoti(doti);
	}
	public int listeDesPrixExcel(List<PrixEmploye> prixEmployes) {
		String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Workbook workbook = new XSSFWorkbook();
		Employe employe = null;
		for (PrixEmploye prixEmploye  : prixEmployes) {
			employe = prixEmploye.getEmploye();
		}
	      Sheet sheet = workbook.createSheet("Liste des prix");
		Row header = sheet.createRow(0);
	      header.createCell(0).setCellValue("Prix");
	      header.createCell(1).setCellValue("Date de Prix");

	      int rowNum = 1;
			for (PrixEmploye prixEmploye  : prixEmployes) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(prixEmploye.getPrix().getLibelle());
	         row.createCell(1).setCellValue(simpleDateFormat.format(prixEmploye.getDateDeObtenation()));
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
	public int listeDesPrixPdf(ArrayList<PrixEmploye> prixEmployes) throws DocumentException, FileNotFoundException {
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	     String fileLocation = "C:/Users/hp/Desktop/";
		Employe employe = null;
		for (PrixEmploye prixEmploye : prixEmployes) {
			employe = prixEmploye.getEmploye();
		}
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(fileLocation +employe.getFirstName() +" "+ employe.getLastName() +"listePrixEmploye.pdf"));

		document.open();
		Font font1 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font1);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);

		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph p1 = new Paragraph("\n\t liste des prix : " + employe.getFirstName() + employe.getLastName() + " \n\r\n", font2);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		PdfPTable table = new PdfPTable(2); // 3 columns.
	    table.setWidthPercentage(100);

		PdfPCell cell2 = new PdfPCell(new Paragraph("prix"));
	    cell2.setBackgroundColor(BaseColor.GRAY);
	    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("date Obtenation"));
	    cell3.setBackgroundColor(BaseColor.GRAY);
	    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		
	    table.addCell(cell2);
		table.addCell(cell3);

		for (PrixEmploye prixEmploye : prixEmployes) {
			PdfPCell cell11 = new PdfPCell(new Paragraph(prixEmploye.getPrix().getLibelle()));
		    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell12 = new PdfPCell(new Paragraph(simpleDateFormat.format(prixEmploye.getDateDeObtenation())));
		    cell12.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell11);
			table.addCell(cell12);
		}

		document.add(table);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		Paragraph p4 = new Paragraph("\n \r\r\r\r signer :", f);
		p4.setAlignment(Element.ALIGN_RIGHT);
		document.add(p4);
		document.add(Chunk.NEWLINE);
		Paragraph p20 = new Paragraph("\n \r\r\r\r marakech  le :" + new Date().toString(), f);
		p20.setAlignment(Element.ALIGN_LEFT);
		document.add(p20);
		document.close();
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "imprimer liste prix employe");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

}
