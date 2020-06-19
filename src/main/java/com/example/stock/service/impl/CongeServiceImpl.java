package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.CongeDao;
import com.example.stock.Dao.EmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Congé;
import com.example.stock.bean.Employe;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.CongeService;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
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
	private EmployeDao employeDao;
	@Autowired
	private TypeCongeeService typeCongeeService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private NotificationEmployeService notificationEmployeService;

	@Override
	public int save(Congé congé) {
		Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
		TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
		if (employe == null) {
			return -2;
		} else if (congé2 == null) {
			return -3;
		} else if (congé.getId() != null) {
			return -1;
		} else {
			if(congé.getCongee().getLibelle().equals("conge exceptionnel")) {
				if ( employe.getSoldeRestantesCongeExceptionnel() < congé.getPeriode()) {
					return -4;
				} else {
					employe.setSoldeRestantesCongeExceptionnel(
							employe.getSoldeRestantesCongeExceptionnel() - congé.getPeriode());
				}
			
			}
			if (congé.getCongee().getLibelle().equals("certificat court duree 3 mois") && congé.getPeriode() > 90) {
				return -5;
			} else if (congé.getCongee().getLibelle().equals("certificat court duree 6 mois")&& (congé.getPeriode() > 180 || congé.getPeriode()<90)) {
				return -6;
			}
			congé.setCongee(congé2);
			congé.setEmploye(employe);
			congé.setDateDeFin(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()));
			congeDao.save(congé);
			TypeNotification typeNotification = notificationService.findByType("save");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
					"update conge");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}
	}

	@Override
	public List<Congé> findCongeByAnne(Integer annee, String type) {
		List<Congé> resultat = new ArrayList<Congé>();
		if (type.equals("longDuree")) {
			List<Congé> congés = findByCongeeLibelle("certificat long duree");
			for (Congé congé : congés) {
				if (DateUlils.verifierdateDebutEtFin(congé.getDateDeDebut(), annee))
					resultat.add(congé);
			}
		} else if (type.equals("courtDuree")) {
			List<Congé> congés = findByCongeeLibelle("certificat court duree");
			for (Congé congé : congés) {
				if (DateUlils.verifierdateDebutEtFin(congé.getDateDeDebut(), annee))
					resultat.add(congé);
			}
		}
		return resultat;
	}

	@Override
	public int update(Congé congé) {
		Employe employe = employeService.findByDoti(congé.getEmploye().getDoti());
		TypeCongee congé2 = typeCongeeService.findByLibelle(congé.getCongee().getLibelle());
		if (employe == null) {
			return -2;
		} else if (congé2 == null) {
			return -3;
		} else {
			congé.setCongee(congé2);
			congé.setDateDeFin(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()));
			congé.setEmploye(employe);
			congeDao.save(congé);
			TypeNotification typeNotification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
					"update conge");
			notificationEmployeService.save(notificationEmploye);
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
		Congé congé = findByid(id);
		congeDao.deleteById(id);
		if (findByid(id) == null) {
			TypeNotification typeNotification = notificationService.findByType("delete");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, congé.getEmploye(),
					new Date(), "delete conge");
			notificationEmployeService.save(notificationEmploye);
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
	public List<Congé> findByEmployeDoti(String doti) {
		return congeDao.findByEmployeDoti(doti);
	}
	public int listeDesCongesExcel(List<Congé> conges) {
		Workbook workbook = new XSSFWorkbook();
		Employe employe = null;
		for (Congé congé : conges) {
			employe = congé.getEmploye();
		}
	      Sheet sheet = workbook.createSheet("Liste Congés");
		Row header = sheet.createRow(0);
	      header.createCell(0).setCellValue("Nom de Employé");
	      header.createCell(1).setCellValue("type de congé");
	      header.createCell(2).setCellValue("Date de début");
	      header.createCell(3).setCellValue("Periode");

	      int rowNum = 1;
	     for (Congé conge : conges) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(conge.getEmploye().getFirstName() +"  "+ conge.getEmploye().getLastName());
	         row.createCell(1).setCellValue(conge.getCongee().getLibelle());
	         row.createCell(2).setCellValue(conge.getDateDeDebut());
	         row.createCell(3).setCellValue(conge.getPeriode());
		}
	     String fileLocation = "C:/Users/hp/Desktop/";
	     try {
		     FileOutputStream outputStream = new FileOutputStream(fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste congé.xlsx");
			workbook.write(outputStream);
		     workbook.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     return 1;
	}
	public int listeDesCongéPdf(List<Congé> conges) throws DocumentException, FileNotFoundException {
		String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Employe employe = null;
		for (Congé congé : conges) {
			employe = congé.getEmploye();
		}
	     String fileLocation = "C:/Users/hp/Desktop/";
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation + employe.getFirstName() + employe.getLastName() + "ListeCongé.pdf"));

		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);
	//} catch (MalformedURLException e) {
		//e.printStackTrace();
	//} catch (IOException e) {
		//e.printStackTrace();
	//}
	Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph("\n\t liste des conge de  :" + employe.getFirstName() + employe.getLastName() + " \n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		PdfPTable table = new PdfPTable(new float[] { 25,40,20,15}); // 3 columns.
		table.setWidthPercentage(100);
		PdfPCell cell1 = new PdfPCell(new Paragraph("Nom De employé"));
	    cell1.setBackgroundColor(BaseColor.GRAY);
	    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell2 = new PdfPCell(new Paragraph("typeDeCongé"));
	    cell2.setBackgroundColor(BaseColor.GRAY);
	    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("date De Début"));
	    cell3.setBackgroundColor(BaseColor.GRAY);
	    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell4 = new PdfPCell(new Paragraph("periode"));
	    cell4.setBackgroundColor(BaseColor.GRAY);
	    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);

		for (Congé congé : conges) {
			PdfPCell cell10 = new PdfPCell(
					new Paragraph(congé.getEmploye().getFirstName() +" "+ congé.getEmploye().getLastName()));
			PdfPCell cell11 = new PdfPCell(new Paragraph(congé.getCongee().getLibelle()));
			PdfPCell cell12 = new PdfPCell(new Paragraph(simpleDateFormat.format(congé.getDateDeDebut())));
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

		Paragraph p4 = new Paragraph("\n \r\r\r\r signer :", f);
		p4.setAlignment(Element.ALIGN_RIGHT);
		document.add(p4);

		Paragraph p20 = new Paragraph("\n \r\r\r\r marakech  le :" + new Date().toString(), f);
		p20.setAlignment(Element.ALIGN_LEFT);
		document.add(p20);
		document.close();
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
				"imprimer liste conge");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}
	public int listeDesCertificatsExcel(List<Congé> conges) {
		String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Workbook workbook = new XSSFWorkbook();
		Employe employe = null;
		for (Congé congé : conges) {
			employe = congé.getEmploye();
		}
	      Sheet sheet = workbook.createSheet("Liste Certificats");
		Row header = sheet.createRow(0);
	      header.createCell(0).setCellValue("Nom de Employé");
	      header.createCell(1).setCellValue("type de congé");
	      header.createCell(2).setCellValue("Date de début");
	      header.createCell(3).setCellValue("Periode");

	      int rowNum = 1;
	     for (Congé conge : conges) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(conge.getEmploye().getFirstName() +"  "+ conge.getEmploye().getLastName());
	         row.createCell(1).setCellValue(conge.getCongee().getLibelle());
	         row.createCell(2).setCellValue(simpleDateFormat.format(conge.getDateDeDebut()));
	         row.createCell(3).setCellValue(conge.getPeriode());
		}
	     String fileLocation = "C:/Users/hp/Desktop/";
	     try {
		     FileOutputStream outputStream = new FileOutputStream(fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste congé.xlsx");
			workbook.write(outputStream);
		     workbook.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     return 1;
	}
	public List<Congé> findCongeCertificat() {
		List<Congé> congés = findByCongeeLibelle("certificat court duree 3 mois");
		congés.addAll(findByCongeeLibelle("certificat long duree"));
		congés.addAll(findByCongeeLibelle("certificat court duree 6 mois"));
		return congés;
	}

	public List<Congé> findListeCertificatByAnnee() {
		List<Congé> congés = findCongeCertificat();
		List<Congé> resultat = new ArrayList<Congé>();
		for (Congé congé : congés) {
			if (DateUlils.VerifieDateSup(DateUlils.getDateFin(congé.getDateDeDebut(), congé.getPeriode()))) {
				resultat.add(congé);
			}
		}
		return resultat;
	}

	@Override
	public List<Congé> findByCongeeLibelleAndDateDeDebut(String libelle, Date date1, Date date2) {
		List<Congé> conges = congeDao.findByCongeeLibelle(libelle);
		List<Congé> resultat = new ArrayList<Congé>();
		for (Congé congé : conges) {
			if(DateUlils.getDateBetween(date1, congé.getDateDeFin(), date2)==1) {
				resultat.add(congé);
			}
		}
		return resultat;
	}

	@Override
	public List<Congé> findByEmployeDotiAndCongeeLibelle(String matricule, String libelle) {
		return congeDao.findByEmployeDotiAndCongeeLibelle(matricule, libelle);
	}

	public void resetSoldeCongéEmploye() {
		List<Employe> employes = employeService.findAll();
		employes.forEach(employe -> {
			employe.setSoldeRestantesCongeExceptionnel(10);
			employeDao.save(employe);
		});
	}

	public int AutoRestSoldeCongeEmplye() {
		Date date = new Date();
		if (((DateUlils.getMonth(date) + 1) == 9) && ((DateUlils.getDay(date) == 1 || DateUlils.getDay(date) == 2
				|| DateUlils.getDay(date) == 3 || DateUlils.getDay(date) == 4) || DateUlils.getDay(date) == 5
				|| DateUlils.getDay(date) == 6 || DateUlils.getDay(date) == 7)) {
			resetSoldeCongéEmploye();
		}
		return 1;
	}
	public int listeDesCertificatsPdf(List<Congé> conges) throws DocumentException, FileNotFoundException {
		String pattern = "yyyy-MM-dd";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Employe employe = null;
	
	
	     String fileLocation = "C:/Users/hp/Desktop/";
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation + "ListeCertificats.pdf"));

		document.open();
		//Image img, img1;
		//try {
			//img = Image.getInstance("fstgIcone.png");
		//	img.setAlignment(Element.ALIGN_TOP);
			//img.setAlignment(Element.ALIGN_RIGHT);
			//document.add(img);
			Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
			Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
			"Faculté des Sciences et Techniques"
							+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
			p0.setAlignment(Element.ALIGN_LEFT);
			document.add(p0);
		//} catch (MalformedURLException e) {
			//e.printStackTrace();
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph("\n\t liste des certificats : \n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		PdfPTable table = new PdfPTable(new float[] { 25,40,20,15}); // 3 columns.
		table.setWidthPercentage(100);
		PdfPCell cell1 = new PdfPCell(new Paragraph("Nom De employé"));
	    cell1.setBackgroundColor(BaseColor.GRAY);
	    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell2 = new PdfPCell(new Paragraph("typeDeCongé"));
	    cell2.setBackgroundColor(BaseColor.GRAY);
	    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("date De Début"));
	    cell3.setBackgroundColor(BaseColor.GRAY);
	    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell4 = new PdfPCell(new Paragraph("periode"));
	    cell4.setBackgroundColor(BaseColor.GRAY);
	    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);

		for (Congé congé : conges) {
			PdfPCell cell10 = new PdfPCell(
					new Paragraph(congé.getEmploye().getFirstName() +" "+ congé.getEmploye().getLastName()));
			PdfPCell cell11 = new PdfPCell(new Paragraph(congé.getCongee().getLibelle()));
			PdfPCell cell12 = new PdfPCell(new Paragraph(simpleDateFormat.format(congé.getDateDeDebut())));
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

		Paragraph p4 = new Paragraph("\n \r\r\r\r signer :", f);
		p4.setAlignment(Element.ALIGN_RIGHT);
		document.add(p4);

		Paragraph p20 = new Paragraph("\n \r\r\r\r marakech  le :" + new Date().toString(), f);
		p20.setAlignment(Element.ALIGN_LEFT);
		document.add(p20);
		document.close();
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
				"imprimer liste conge");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

}
