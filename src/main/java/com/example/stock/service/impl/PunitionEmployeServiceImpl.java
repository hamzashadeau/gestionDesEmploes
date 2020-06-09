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

import com.example.stock.Dao.PunitionEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Notification;
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
			Notification notification = notificationService.findByType("save");
			NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "save punition employe");
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
			Notification notification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(notification, employe, new Date(), "update punition employe");
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
		Notification notification = notificationService.findByType("delete");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification, punitionEmploye.getEmploye(), new Date(), "delete punition employe");
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
	public List<PunitionEmploye> findPunitionDeEmploye(Employe employe) {
		List<PunitionEmploye> punitionEmployes = findByEmployeDoti(employe.getDoti());
		List<PunitionEmploye> resultat = new ArrayList<PunitionEmploye>();
		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			if (DateUlils.verifierDateSup(employe.getDernierGrade().getDateDeAffectation(),
					punitionEmploye.getDateObtenation()))
				resultat.add(punitionEmploye);
		}
		return resultat;
	}

	public int listeDespunitionsPdf(ArrayList<PunitionEmploye> punitionEmployes)
			throws DocumentException, FileNotFoundException {
		String fullName = null;
		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			fullName = punitionEmploye.getEmploye().getFirstName() + punitionEmploye.getEmploye().getLastName();
		}
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("listePunitionsEmploye.pdf"));

		document.open();
		Image img, img1;
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
		Paragraph p1 = new Paragraph("\n\t liste des punitions" + fullName + " \n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		PdfPTable table = new PdfPTable(3); // 3 columns.

		PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
		PdfPCell cell2 = new PdfPCell(new Paragraph("punition"));
		PdfPCell cell3 = new PdfPCell(new Paragraph("date Obtenation"));

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);

		for (PunitionEmploye punitionEmploye : punitionEmployes) {
			PdfPCell cell10 = new PdfPCell(new Paragraph(punitionEmploye.getEmploye().getFirstName() + punitionEmploye.getEmploye().getLastName()));
			PdfPCell cell11 = new PdfPCell(new Paragraph(punitionEmploye.getPunition().getLibelle()));
			PdfPCell cell12 = new PdfPCell(new Paragraph(punitionEmploye.getDateObtenation().toString()));

			table.addCell(cell10);
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

		Paragraph p20 = new Paragraph("\n \r\r\r\r marakech  le :" + new Date().toString(), f);
		p20.setAlignment(Element.ALIGN_LEFT);
		document.add(p20);
		document.close();
		return 1;
	}

}
