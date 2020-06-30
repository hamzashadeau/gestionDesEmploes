package com.example.stock.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.border.SoftBevelBorder;
import javax.xml.transform.TransformerException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.Dao.DemaneDeDocumentDao;
import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.TypeDocumentDao;
import com.example.stock.Utilis.HashUtil;
import com.example.stock.bean.Congé;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.TypeNotification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.bean.TypeDocument;
import com.example.stock.service.facade.DemandeDeDocumentService;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.RapportDeEvaluationService;
import com.example.stock.service.facade.SalaireEmployeService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class DemandeDeDocumentServiceImpl implements DemandeDeDocumentService {
	@Autowired
	private DemaneDeDocumentDao demaneDeDocumentDao;
	@Autowired
	private RapportDeEvaluationService rapportDeEvaluationService;
	@Autowired
	private EmployeService employeService;
	@Autowired
	private EmployeDao employeDao;
	@Autowired
	private TypeDocumentDao typeDocumentDao;
	@Autowired
	private SalaireEmployeService salaireEmployeService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private NotificationEmployeService notificationEmployeService;

	@Override
	public int save(DemaneDeDocument demaneDeDocument) {
		Employe employe = employeService.findByDoti(demaneDeDocument.getEmploye().getDoti());
		TypeDocument typeDocument = typeDocumentDao.findByLibelle(demaneDeDocument.getTypeDeDocument().getLibelle());
		if (demaneDeDocument.getId() != null) {
			return -1;
		} else if (employe == null) {
			return -2;
		} else if (typeDocument == null) {
			return -3;
		} else {
			demaneDeDocument.setTypeDeDocument(typeDocument);
			demaneDeDocument.setEmploye(employe);
			demaneDeDocument.setDateDemande(new Date());
			demaneDeDocument.setEtat("non traité");
			demaneDeDocumentDao.save(demaneDeDocument);
			TypeNotification typeNotification = notificationService.findByType("save");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
					"save demande");
			notificationEmployeService.save(notificationEmploye);
			try {
				HashUtil.sendmail(employe.getEmail(), "sauvegarde de demande de document",
						"votre demande de "+demaneDeDocument.getTypeDeDocument().getLibelle() +"est bien sauvegarder");
			} catch (MessagingException | IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}

	@Override
	public int update(DemaneDeDocument demaneDeDocument) {
		Employe employe = employeService.findByDoti(demaneDeDocument.getEmploye().getDoti());
		TypeDocument typeDocument = typeDocumentDao.findByLibelle(demaneDeDocument.getTypeDeDocument().getLibelle());
		if (employe == null) {
			return -2;
		} else if (typeDocument == null) {
			return -3;
		} else {
			demaneDeDocument.setTypeDeDocument(typeDocument);
			employeDao.save(employe);
			demaneDeDocument.setEmploye(employe);
			demaneDeDocument.setDateDemande(new Date());
			demaneDeDocument.setEtat("non traité");
			demaneDeDocumentDao.save(demaneDeDocument);
			TypeNotification typeNotification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
					"update demande");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}
	}

	public int infoEmployePdf(Employe employe) throws DocumentException, FileNotFoundException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fileLocation = "C:/Users/hp/Desktop/";
		Document document = new Document();
		PdfWriter writer =PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation + employe.getFirstName() + employe.getLastName() + "Info.pdf"));
		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);


		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph("  Informations en détailes sur cet " + employe.getFirstName() + " " + employe.getLastName() + "\n\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		Font font1 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
if(employe.getEnfants()== null) {
	employe.setEnfants(0);
}
		ParagraphBorder border = new ParagraphBorder();
		writer.setPageEvent(border);
		Paragraph p = new Paragraph();
		border.setActive(true);
		Paragraph p3 = new Paragraph(" information personnel \n", font1);
		p3.setAlignment(Element.ALIGN_CENTER);
		document.add(p3);
		p.add("*Nom Complet: " + employe.getFirstName() + " "+ employe.getLastName() +"                      *Cin :"+ employe.getCin() +
				" \r" + "*Numero:"+ employe.getDoti()+ "                                                   *Situation Familiale:"+ employe.getSituationFamiliale() +
				"\r" + "*Enfant: "+ employe.getEnfants()+ "                                                                *Email :"+ employe.getEmail() +
				"\r" + " *Telephone: +212"+ employe.getTel()+ "                                 *ville de Residence:"+ employe.getLieuDeResedence() +
				"\r" + "*Adresse: "+ employe.getAdresse()+ "                            *Fonction :"+ employe.getFonction().getLibelle() +"\r" + "");

		document.add(p);
		border.setActive(false);
		Paragraph p55 = new Paragraph("\n");
		document.add(p55);
		border.setActive(true);
		if(employe.getDep().getFullname()!= null) {
			Paragraph p22 = new Paragraph("Information sur le departement:", font1);
			p22.setAlignment(Element.ALIGN_CENTER);
			document.add(p22);
			Paragraph p21 = new Paragraph();
			p21.add("*Libelle:" + employe.getDep().getNom()+ "                                               * chef departement:"+  employe.getDep().getFullname());
			document.add(p21);			
		}
		border.setActive(false);
		Paragraph p56 = new Paragraph("\n");
		document.add(p56);
		if(employe.getDernierNote() != null) {
			border.setActive(true);
			Paragraph p22 = new Paragraph("Information sur la dernier note :", font1);
			p22.setAlignment(Element.ALIGN_CENTER);
			document.add(p22);
			Paragraph p21 = new Paragraph();
			p21.add("*moyen Génerale:" + employe.getDernierNote().getMoyenGeneral().toString()+ "                                               * Mention:"+  employe.getDernierNote().getMention()
				+"\n                                              date de note:"+ simpleDateFormat.format(employe.getDernierNote().getDate()));
			document.add(p21);
			border.setActive(false);			
			Paragraph p57 = new Paragraph("\n");
			document.add(p57);
		}
		if(employe.getDernierGrade() != null) {
		border.setActive(true);
		Paragraph p27 = new Paragraph("Information sur la dernier grade :", font1);
		p27.setAlignment(Element.ALIGN_CENTER);
		document.add(p27);
		Paragraph p28 = new Paragraph();
		p28.add("*Libelle:" + employe.getDernierGrade().getGrade().getLibelle()+ "                                               * Date affectation:"+  simpleDateFormat.format(employe.getDernierGrade().getDateDeAffectation()));
		document.add(p28);
		border.setActive(false);
		}
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
				"imprimer info employe");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

	public int rapportPdf(RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException {
		String pattern = "yyyy-MM-dd";
		String fileLocation = "C:/Users/hp/Desktop/";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation + rapportDeEvaluation.getEmploye().getFirstName()
						+ rapportDeEvaluation.getEmploye().getLastName() + "Rapport.pdf"));

		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);

		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph(" Rappot de  " + rapportDeEvaluation.getEmploye().getFirstName() + " "
				+ rapportDeEvaluation.getEmploye().getLastName() + "  pour Avancement de grade:"
				+ rapportDeEvaluation.getNouveauGrade().getGrade().getLibelle() + "\n\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		Font font1 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

		ParagraphBorder border = new ParagraphBorder();
		writer.setPageEvent(border);
		Paragraph p = new Paragraph();
		border.setActive(true);
		Paragraph p3 = new Paragraph(" information personnel \n", font1);
		p3.setAlignment(Element.ALIGN_CENTER);
		document.add(p3);
		p.add("*Nom Complet: " + rapportDeEvaluation.getEmploye().getFirstName() + " "
				+ rapportDeEvaluation.getEmploye().getLastName() + "                          *Adresse :"
				+ rapportDeEvaluation.getEmploye().getAdresse() + " \r" + "*Numero:"
				+ rapportDeEvaluation.getEmploye().getDoti()
				+ "                                                   *Situation Familiale:"
				+ rapportDeEvaluation.getEmploye().getSituationFamiliale() + "\r" + "*Cin: "
				+ rapportDeEvaluation.getEmploye().getCin()
				+ "                                                     *Email :"
				+ rapportDeEvaluation.getEmploye().getEmail() + "\r" + "");

		document.add(p);
		border.setActive(false);
		Paragraph p55 = new Paragraph("\n");
		document.add(p55);
		border.setActive(true);
		Paragraph p22 = new Paragraph("Information sur le Grade:", font1);
		p22.setAlignment(Element.ALIGN_CENTER);
		document.add(p22);
		Paragraph p21 = new Paragraph();
		p21.add("*Libelle:" + rapportDeEvaluation.getNouveauGrade().getGrade().getLibelle()
				+ "                                               * Date Affectation:"
				+ simpleDateFormat.format(rapportDeEvaluation.getNouveauGrade().getDateDeAffectation()));
		document.add(p21);
//les notes    
		border.setActive(false);
		Paragraph p5 = new Paragraph("\n les notes :\n\n", font1);
		p5.setAlignment(Element.ALIGN_CENTER);
		document.add(p5);

		PdfPTable table = new PdfPTable(3); // 3 columns.
		table.setWidthPercentage(100);

		PdfPCell cell1 = new PdfPCell(new Paragraph("moyen "));
		cell1.setBackgroundColor(BaseColor.GRAY);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell2 = new PdfPCell(new Paragraph("mention"));
		cell2.setBackgroundColor(BaseColor.GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("date"));
		cell3.setBackgroundColor(BaseColor.GRAY);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		for (NoteGeneralDeAnnee note : rapportDeEvaluation.getNoteGenerale()) {
			PdfPCell cell4 = new PdfPCell(new Paragraph(note.getMoyenGeneral().toString()));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell5 = new PdfPCell(new Paragraph(note.getMention()));
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell6 = new PdfPCell(new Paragraph(simpleDateFormat.format(note.getDate())));
			cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
		}
		document.add(table);

		// les formation
		Paragraph p6 = new Paragraph(" les formations :\n\n", font1);
		p6.setAlignment(Element.ALIGN_CENTER);
		document.add(p6);

		PdfPTable table1 = new PdfPTable(new float[] { 20,20,20,15,10,15}); // 3 columns.
		table1.setWidthPercentage(100);
		PdfPCell cell10 = new PdfPCell(new Paragraph("attestation "));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell11 = new PdfPCell(new Paragraph("domaine"));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell12 = new PdfPCell(new Paragraph("etablissement"));
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell13 = new PdfPCell(new Paragraph("mention"));
		cell13.setBackgroundColor(BaseColor.GRAY);
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell14 = new PdfPCell(new Paragraph("ville"));
		cell14.setBackgroundColor(BaseColor.GRAY);
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell15 = new PdfPCell(new Paragraph("date"));
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell15.setBackgroundColor(BaseColor.GRAY);

		table1.addCell(cell10);
		table1.addCell(cell11);
		table1.addCell(cell12);
		table1.addCell(cell13);
		table1.addCell(cell14);
		table1.addCell(cell15);
		for (Formation note : rapportDeEvaluation.getFormation()) {
			PdfPCell cell4 = new PdfPCell(new Paragraph(note.getAttestation()));
			PdfPCell cell5 = new PdfPCell(new Paragraph(note.getDomaine()));
			PdfPCell cell6 = new PdfPCell(new Paragraph(note.getEtablissement()));
			PdfPCell cell7 = new PdfPCell(new Paragraph(note.getMention()));
			PdfPCell cell8 = new PdfPCell(new Paragraph(note.getVille()));
			PdfPCell cell9 = new PdfPCell(new Paragraph(simpleDateFormat.format(note.getAnnee())));
			table1.addCell(cell4);
			table1.addCell(cell5);
			table1.addCell(cell6);
			table1.addCell(cell7);
			table1.addCell(cell8);
			table1.addCell(cell9);
		}
		document.add(table1);

		// les prix
		Paragraph p7 = new Paragraph(" les prix :\n\n", font1);
		p7.setAlignment(Element.ALIGN_CENTER);
		document.add(p7);

		PdfPTable table2 = new PdfPTable(2); // 3 columns.

		PdfPCell cell16 = new PdfPCell(new Paragraph("prix "));
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell16.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell17 = new PdfPCell(new Paragraph("date Obtenation"));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell17.setBackgroundColor(BaseColor.GRAY);

		table2.addCell(cell16);
		table2.addCell(cell17);
		for (PrixEmploye note : rapportDeEvaluation.getPrix()) {
			PdfPCell cell4 = new PdfPCell(new Paragraph(note.getPrix().getLibelle()));
			PdfPCell cell5 = new PdfPCell(new Paragraph(simpleDateFormat.format(note.getDateDeObtenation())));

			table2.addCell(cell4);
			table2.addCell(cell5);
		}
		document.add(table2);

		// les punition
		Paragraph p8 = new Paragraph(" les punitions :\n\n", font1);
		p8.setAlignment(Element.ALIGN_CENTER);
		document.add(p8);

		PdfPTable table3 = new PdfPTable(2); // 3 columns.

		PdfPCell cell18 = new PdfPCell(new Paragraph("punition "));
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell18.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell19 = new PdfPCell(new Paragraph("date Obtenation"));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBackgroundColor(BaseColor.GRAY);

		table3.addCell(cell16);
		table3.addCell(cell17);
		for (PunitionEmploye note : rapportDeEvaluation.getPunition()) {
			PdfPCell cell4 = new PdfPCell(new Paragraph(note.getPunition().getLibelle()));
			PdfPCell cell5 = new PdfPCell(new Paragraph(simpleDateFormat.format(note.getDateObtenation())));

			table3.addCell(cell4);
			table3.addCell(cell5);
		}
		document.add(table3);

		// les resultats
		Paragraph p9 = new Paragraph(" les resultats :\n\n", font);
		p9.setAlignment(Element.ALIGN_CENTER);
		document.add(p9);

		PdfPTable table4 = new PdfPTable(3); // 3 columns.

		PdfPCell cell20 = new PdfPCell(new Paragraph("moyen "));
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell20.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell21 = new PdfPCell(new Paragraph("mention"));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell22 = new PdfPCell(new Paragraph("remarque"));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBackgroundColor(BaseColor.GRAY);

		PdfPCell cell23 = new PdfPCell(new Paragraph(rapportDeEvaluation.getMoyen().toString()));
		PdfPCell cell24 = new PdfPCell(new Paragraph(rapportDeEvaluation.getMention()));
		PdfPCell cell25 = new PdfPCell(new Paragraph(rapportDeEvaluation.getRemarques()));
		table4.addCell(cell20);
		table4.addCell(cell21);
		table4.addCell(cell22);
		table4.addCell(cell23);
		table4.addCell(cell24);
		table4.addCell(cell25);
		document.add(table4);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		Paragraph p4 = new Paragraph("\n  signer :", f);
		p4.setAlignment(Element.ALIGN_LEFT);
		document.add(p4);

		Paragraph p20 = new Paragraph("\n  marakech  le :" + simpleDateFormat.format(new Date()), f);
		p20.setAlignment(Element.ALIGN_RIGHT);
		document.add(p20);
		document.close();
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,
				rapportDeEvaluation.getEmploye(), new Date(), "imprimer rapport evaluation");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

	public int attestationDeSalaire(DemaneDeDocument demaneDeDocument)
			throws DocumentException, TransformerException, AddressException, MessagingException, IOException {
		String pattern = "yyyy-MM-dd";
		String fileLocation = "C:/Users/hp/Desktop/documentNonSigne/";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Employe employe = demaneDeDocument.getEmploye();
		SalaireEmploye salaireEmploye = salaireEmployeService.findByEmployeDoti(employe.getDoti());

		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation +simpleDateFormat.format(demaneDeDocument.getDateDemande())
								+ "attestationDeSalaire" + demaneDeDocument.getEmploye().getDoti() + ".pdf"));

		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);

		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph("\n\t ATTESTATION DE salaire\n\n ", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		Paragraph p = new Paragraph();
		p.add(" \r\n" + "le service RH de  l'etablissement de science et de technique atteste par la persente que Mr  "
				+ employe.getFirstName() +" "+ employe.getLastName() + "titulaire  De la cin N°" + employe.getCin()
				+ " ,travaille en qualite dans " + "la faculté de science et technique depuis le"
				+ employe.getDateEntree() + ",et percoit un salaire annuel net de :" + salaireEmploye.getSalaireNet()
				+ "DHS,dont le detail est le suivant :\r\n" + "les emouluments:\n\r" + "Allocation De Encadrement:"
				+ salaireEmploye.getAllocationDeEncadrement().getMontant() + "\n\r" + "Idem De La Residence:"
				+ salaireEmploye.getIdemDeLaResidence().getMontant() + "\n\r" + "Idem Famialie le Marocaine:"
				+ salaireEmploye.getIdemFamialieleMarocaine().getMontant() + "\n\r" + "Allocation De Enseignement:"
				+ salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant() + "\n\r" + "les revenues" + "\n\r"
				+ "Assurance Maladie Obligatoire :" + salaireEmploye.getAssuranceMaladieObligatoire().getMontant()
				+ "\n\r" + "Caisse Marocaine De retrait:" + salaireEmploye.getCaisseMarocaineDeretrait().getMontant()
				+ "\n\r" + "Mutuelle Caisse Retrait Et Deces:"
				+ salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant() + "\n\r" + "Impot Sur Le Revenu:"
				+ salaireEmploye.getImpotSurLeRevenu().getMontant());

		p.setAlignment(Element.ALIGN_LEFT);
		document.add(p);

		Paragraph p2 = new Paragraph();
		p2.add(" \n\r\r CETTE ATTESTATION LUI EST DELIVREE POUR SERVIR ET VALOIR CE QUE DE DROIT.\r\n"
				+ " \n \r\r\r\r Signé ");// no alignment
		p2.setAlignment(Element.ALIGN_CENTER);
		document.add(p2);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		Paragraph p4 = new Paragraph("\n \r\r marakech  le :" + simpleDateFormat.format(new Date()), f);
		p4.setAlignment(Element.ALIGN_LEFT);
		document.add(p4);
		document.close();
		System.out.println(demaneDeDocument.getCopieEmail());
		if(demaneDeDocument.getCopieEmail().equals("oui")) {
			demaneDeDocument.setEtat("non signe");
			HashUtil.sendmail(employe.getEmail(), "demande en traitement",
					"votre demande attestation de salaire est en traitement vous recevez une copie scanné dans votre mail dans un durée 24 heures au maximum");
			
		}else {
			demaneDeDocument.setEtat("traité");
			HashUtil.sendmail(employe.getEmail(), "demande en traitement",
					"votre demande attestation de salaire est en traitement vous trouverez votre document dans le guichet de etablissement dans un durée 24 heures au maximum");
		}demaneDeDocumentDao.save(demaneDeDocument);
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
				"imprimer attestation de salaire");
		notificationEmployeService.save(notificationEmploye);
		HashUtil.sendmail(employe.getEmail(), "demande en traitement",
				"votre demande attestation de salaire est en traitement voua allez avoir le document  dans un durée 24 heures au maximum");
		return 1;
	}

	public int attestationDeTravail(DemaneDeDocument demaneDeDocument)
			throws DocumentException, TransformerException, AddressException, MessagingException, IOException {
		String pattern = "yyyy-MM-dd";
		String fileLocation = "C:/Users/hp/Desktop/documentNonSigne/";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Employe employe = demaneDeDocument.getEmploye();
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream(fileLocation + simpleDateFormat.format(demaneDeDocument.getDateDemande())+ "attestaionDeTravail" + demaneDeDocument.getEmploye().getDoti() + ".pdf"));

		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);

		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph("\n\t ATTESTATION DE Travail\n\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

		Paragraph p = new Paragraph();
		p.add("\n\nJe soussigné  le service RH de la faculté de la science et de technique atteste que :" + "\n\r"
				+ "Mr (Mme) (Melle)    :  " + employe.getFirstName() +" "+ employe.getLastName() + "\n\r" + "D.O.T.I     : "
				+ employe.getDoti() + "\n\r" + "CIN     : " + employe.getCin() + "\n\r" + "Date de recrutement  : "
				+ simpleDateFormat.format(employe.getDateEntree()) + "\n\r" + "Grade: " + employe.getDernierGrade().getGrade().getLibelle()
				+ "\n\r" + "fonction: " + employe.getFonction().getLibelle() + "\n\r"
				+ "Lieu de travail : la faculté de la science et de technique" + "\n\r");
		p.setAlignment(Element.ALIGN_LEFT);

		document.add(p);

		Paragraph p2 = new Paragraph();
		p2.add(" \n\n \r\r\r\rLa présente attestation est délivrée a l’intéressé(e) pour servir et valoir ce que de droit \n \r\r\r\r Signé ");// no
																																				// alignment
		p2.setAlignment(Element.ALIGN_CENTER);
		document.add(p2);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		Paragraph p4 = new Paragraph("\n \r\r\r\r marakech  le :" + new Date().toString(), f);
		p4.setAlignment(Element.ALIGN_LEFT);
		document.add(p4);
		document.close();
		// if(demaneDeDocument.getManiereDeRetrait().equals("gmail")) {
		// try {
		// HashUtil.sendmail(demaneDeDocument.getEmploye().getEmail(), "attestation de
		// travail", "bon reception","C:/Users/hp/eclipse-workspace/gestionDesEmploye/"
		// + demaneDeDocument.getEmploye().getFirstName() +
		// demaneDeDocument.getEmploye().getLastName() + "attestaionDeTravail" +
		// demaneDeDocument.getEmploye().getDoti() + ".pdf");
		// } catch (AddressException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (MessagingException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		if(demaneDeDocument.getCopieEmail().equals("oui")) {
			demaneDeDocument.setEtat("non signe");
//			HashUtil.sendmail(employe.getEmail(), "demande en traitement",
	//				"votre demande attestation de travail est en traitement vous recevez une copie scanné dans votre mail dans un durée 24 heures au maximum");
			
		}else {
			demaneDeDocument.setEtat("traité");
	//		HashUtil.sendmail(employe.getEmail(), "demande en traitement",
		//			"votre demande attestation de travail est en traitement vous trouverez votre document dans le guichet de etablissementl dans un durée 24 heures au maximum");
		}
		demaneDeDocumentDao.save(demaneDeDocument);
		TypeNotification typeNotification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification, employe, new Date(),
				"imprimer attestation de travail");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

	public File convert(MultipartFile file) throws IOException {
		String fileLocation = "C:/Users/hp/Desktop/documentScanne/";
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(fileLocation + convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
}
	public int sendmail(Long id,String email, String subject, String content, MultipartFile file)
			throws AddressException, MessagingException, IOException, TransformerException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("etablissementfstg@gmail.com", "Fstg-2020/2021");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(email, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject(subject);
		msg.setContent(content, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(content, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile(convert(file));
		multipart.addBodyPart(attachPart);
		// MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		// attachmentBodyPart.attachFile(new File("path/to/file"))
		msg.setContent(multipart);

		// attachPart.attachFile();
		Transport.send(msg);
		DemaneDeDocument deDocument = findByid(id);
		deDocument.setEtat("traité");
		demaneDeDocumentDao.save(deDocument);
		return 1;
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
		DemaneDeDocument demaneDeDocument = findByid(id);
		demaneDeDocumentDao.deleteById(id);
		if (findByid(id) == null) {
			TypeNotification typeNotification = notificationService.findByType("delete");
			NotificationEmploye notificationEmploye = new NotificationEmploye(typeNotification,
					demaneDeDocument.getEmploye(), new Date(), "delete demande");
			notificationEmployeService.save(notificationEmploye);
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
	public List<DemaneDeDocument> findByEmployeDoti(String doti) {
		return demaneDeDocumentDao.findByEmployeDoti(doti);
	}

	@Override
	public List<DemaneDeDocument> findByEtat(String etat) {
		return demaneDeDocumentDao.findByEtat(etat);
	}

	public int listeDesDemandesExcel(List<DemaneDeDocument> demandes) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Workbook workbook = new XSSFWorkbook();
		Employe employe = null;
		for (DemaneDeDocument demaneDeDocument : demandes) {
			employe = demaneDeDocument.getEmploye();
		}
		Sheet sheet = workbook.createSheet("Liste demandes");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Nom de Employé");
		header.createCell(1).setCellValue("type de document");
		header.createCell(2).setCellValue("Date de demande");
		header.createCell(3).setCellValue("Maniere de retrait");

		int rowNum = 1;
		for (DemaneDeDocument demaneDeDocument : demandes) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(
					demaneDeDocument.getEmploye().getFirstName() + "  " + demaneDeDocument.getEmploye().getLastName());
			row.createCell(1).setCellValue(demaneDeDocument.getTypeDeDocument().getLibelle());
			row.createCell(2).setCellValue(simpleDateFormat.format(demaneDeDocument.getDateDemande()));
			row.createCell(3).setCellValue(demaneDeDocument.getCopieEmail().toString());
		}
		String fileLocation = "C:/Users/hp/Desktop/";
		try {
			FileOutputStream outputStream = new FileOutputStream(
					fileLocation + employe.getFirstName() + " " + employe.getLastName() + "Liste demande.xlsx");
			workbook.write(outputStream);
			workbook.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 1;
	}

	public int listeDesDemandePdf(List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException {
		Employe employe = null;
		for (DemaneDeDocument demaneDeDocument : demandes) {
			employe = demaneDeDocument.getEmploye();
		}
		String fileLocation = "C:/Users/hp/Desktop/";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(
				fileLocation + employe.getFirstName() + employe.getLastName() + "listedemandes.pdf"));

		document.open();
		Font font2 = FontFactory.getFont(FontFactory.TIMES, 9, BaseColor.BLACK);
		Paragraph p0 = new Paragraph("ROYAUME DU MAROC" + "\n" + "Université Cadi Ayyad." + "\n" +
		"Faculté des Sciences et Techniques"
						+ "\n" + "Gueliz-Marrakech" + "\n" + "\n" , font2);
		p0.setAlignment(Element.ALIGN_LEFT);
		document.add(p0);

		Font font = FontFactory.getFont(FontFactory.TIMES, 18, Font.UNDERLINE);
		Paragraph p1 = new Paragraph(
				"\n\t liste des demandes" + employe.getFirstName() + employe.getLastName() + "\n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		PdfPTable table = new PdfPTable(new float[] { 30, 30, 15, 25 }); // 3 columns.
		table.setWidthPercentage(100);
		PdfPCell cell1 = new PdfPCell(new Paragraph("Nom Complet"));
		cell1.setBackgroundColor(BaseColor.GRAY);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell2 = new PdfPCell(new Paragraph("Type de document"));
		cell2.setBackgroundColor(BaseColor.GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell3 = new PdfPCell(new Paragraph("Date de demande"));
		cell3.setBackgroundColor(BaseColor.GRAY);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell4 = new PdfPCell(new Paragraph("Copie en Email"));
		cell4.setBackgroundColor(BaseColor.GRAY);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);

		for (DemaneDeDocument demaneDeDocument : demandes) {
			PdfPCell cell10 = new PdfPCell(
					new Paragraph(demaneDeDocument.getEmploye().getFirstName() + employe.getLastName()));
			PdfPCell cell11 = new PdfPCell(new Paragraph(demaneDeDocument.getTypeDeDocument().getLibelle()));
			PdfPCell cell12 = new PdfPCell(new Paragraph(simpleDateFormat.format(demaneDeDocument.getDateDemande())));
			PdfPCell cell13 = new PdfPCell(new Paragraph(demaneDeDocument.getCopieEmail()));

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
				"imprimer liste des demandes");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}

	@Override
	public List<DemaneDeDocument> findByTypeDeDocumentLibelleAndEmployeDoti(String libelle, String doti) {
		return demaneDeDocumentDao.findByTypeDeDocumentLibelleAndEmployeDoti(libelle, doti);
	}

}
