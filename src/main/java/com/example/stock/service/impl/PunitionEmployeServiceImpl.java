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

import com.example.stock.Dao.PunitionEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.Punition;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.PunitionEmployeService;
import com.example.stock.service.facade.PunitionService;
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
public class PunitionEmployeServiceImpl implements PunitionEmployeService {
	@Autowired
	private PunitionEmployeDao punitionEmployeDao;
	@Autowired
	private EmployeService employeService;
	@Autowired
	private PunitionService punitionService;
	@Autowired
	private NotificationEmployeService notificationEmployeService;
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public int save(PunitionEmploye punitionEmploye) {
		Employe employe = employeService.findByDoti(punitionEmploye.getEmploye().getDoti());
		Punition punition = punitionService.findByLibelle(punitionEmploye.getPunition().getLibelle());
		if (punitionEmploye.getId() != null) {
			return -1;
		} else if (employe == null) {
			return -2;
		} else if (punition == null) {
			return -3;
		} else {
			punitionEmploye.setEmploye(employe);
			punitionEmploye.setPunition(punition);
			punitionEmployeDao.save(punitionEmploye);
			TypeNotification typeNotification = notificationService.findByType("save");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "save punition employe");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}
	}
	@Override
	public int update(PunitionEmploye punitionEmploye) {
		Employe employe = employeService.findByDoti(punitionEmploye.getEmploye().getDoti());
		Punition punition = punitionService.findByLibelle(punitionEmploye.getPunition().getLibelle());
		if (punitionEmploye.getId() == null) {
			return -1;
		} else if (employe == null) {
			return -2;
		} else if (punition == null) {
			return -3;
		} else {
			punitionEmploye.setEmploye(employe);
			punitionEmploye.setPunition(punition);
			punitionEmployeDao.save(punitionEmploye);
			TypeNotification typeNotification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "update punition employe");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}
	}

	@Override
	public PunitionEmploye findByid(Long id) {
		if (punitionEmployeDao.findById(id).isPresent()) {
			return punitionEmployeDao.findById(id).get();
		} else
			return null;
	}

	@Override
	public int deleteById(Long id) {
		PunitionEmploye punitionEmploye = findByid(id);
		Employe employe = punitionEmploye.getEmploye();
		TypeNotification typeNotification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(), "delete punition employe");
		notificationEmployeService.save(notificationEmploye);
		punitionEmployeDao.deleteById(id);
		if (findByid(id) == null) {
			return 1;
		} else
			return -1;
	}

	@Override
	public List<PunitionEmploye> findAll() {
		return punitionEmployeDao.findAll();
	}

	@Override
	public List<PunitionEmploye> findByEmployeEmail(String email) {
		return punitionEmployeDao.findByEmployeEmail(email);
	}

	@Override
	public List<PunitionEmploye> findByPunitionLibelle(String libelle) {
		return punitionEmployeDao.findByPunitionLibelle(libelle);
	}

	@Override
	public List<PunitionEmploye> findByPunitionType(String type) {
		return punitionEmployeDao.findByPunitionType(type);
	}

	@Override
	public List<PunitionEmploye> findByEmployeDoti(String doti) {
		return punitionEmployeDao.findByEmployeDoti(doti);
	}

	@Override
	public List<PunitionEmploye> findByEmployeId(Long id) {
		return punitionEmployeDao.findByEmployeId(id);
	}

	@Override
	public List<PunitionEmploye> findPunitionDeEmploye(String doti) {
		Employe employe = employeService.findByDoti(doti);
		List<PunitionEmploye> punitionEmployes = findByEmployeDoti(employe.getDoti());
		List<PunitionEmploye> resultat = new ArrayList<PunitionEmploye>();
		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			if (DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(),
					punitionEmploye.getDateObtenation()))
				resultat.add(punitionEmploye);
		}
		return resultat;
	}
	public int listeDesPunitionsExcel(List<PunitionEmploye> punitionEmployes) {
		String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Workbook workbook = new XSSFWorkbook();
		Employe employe = null;
		for (PunitionEmploye punitionEmploye  : punitionEmployes) {
			employe = punitionEmploye.getEmploye();
		}
	      Sheet sheet = workbook.createSheet("Liste punitions");
		Row header = sheet.createRow(0);
	      header.createCell(0).setCellValue("Punition");
	      header.createCell(1).setCellValue("Date de Punition");

	      int rowNum = 1;
			for (PunitionEmploye punitionEmploye  : punitionEmployes) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(punitionEmploye.getPunition().getLibelle());
	         row.createCell(1).setCellValue(simpleDateFormat.format(punitionEmploye.getDateObtenation()));
		}
	     String fileLocation = "C:/Users/hp/Desktop/";
	     try {
		     FileOutputStream outputStream = new FileOutputStream(fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste punitions.xlsx");
			workbook.write(outputStream);
		     workbook.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     return 1;
	}
	public int listeDespunitionsPdf(ArrayList<PunitionEmploye> punitionEmployes)
			throws DocumentException, FileNotFoundException {
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	     String fileLocation = "C:/Users/hp/Desktop/";
		String fullName = null;
		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			fullName = punitionEmploye.getEmploye().getFirstName() +" "+ punitionEmploye.getEmploye().getLastName();
		}
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(fileLocation + fullName +"listePunitionsEmploye.pdf"));

		document.open();
		Font font1 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font1);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);

		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph p1 = new Paragraph("\n\t liste des punitions :" + fullName + " \n\r\n", font2);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		PdfPTable table = new PdfPTable(2); // 3 columns.

		PdfPCell cell2 = new PdfPCell(new Paragraph("Punition"));
	    cell2.setBackgroundColor(BaseColor.GRAY);
	    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("Date Obtenation"));
	    cell3.setBackgroundColor(BaseColor.GRAY);
	    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell2);
		table.addCell(cell3);

		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			PdfPCell cell11 = new PdfPCell(new Paragraph(punitionEmploye.getPunition().getLibelle()));
		    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell12 = new PdfPCell(new Paragraph(simpleDateFormat.format(punitionEmploye.getDateObtenation())));
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
		return 1;
	}

}
