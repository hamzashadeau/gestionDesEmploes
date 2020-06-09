package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SAAJMetaFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.GradeEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.Congé;
import com.example.stock.bean.Departement;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Fonction;
import com.example.stock.bean.Formation;
import com.example.stock.bean.Grade;
import com.example.stock.bean.GradeEmploye;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.Notification;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.service.facade.CongeService;
import com.example.stock.service.facade.DepartementService;
import com.example.stock.service.facade.EmolumentsService;
import com.example.stock.service.facade.EmployeService;
import com.example.stock.service.facade.FonctionService;
import com.example.stock.service.facade.FormationService;
import com.example.stock.service.facade.GradeEmployeService;
import com.example.stock.service.facade.GradeService;
import com.example.stock.service.facade.NoteGeneraleService;
import com.example.stock.service.facade.NotificationEmployeService;
import com.example.stock.service.facade.NotificationService;
import com.example.stock.service.facade.PrixEmployeService;
import com.example.stock.service.facade.PunitionEmployeService;
import com.example.stock.service.facade.RapportDeEvaluationService;
import com.example.stock.service.facade.RevenuService;
import com.example.stock.service.facade.SalaireEmployeService;
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

import javafx.scene.control.Label;

@Service
public class EmployeeServiceImpl implements EmployeService {
	@Autowired
	private EmployeDao employeDao;
	@Autowired
	private SalaireEmployeService salaireEmployeService;
	@Autowired
	private GradeEmployeService gradeEmployeService;
	@Autowired
	private NotificationEmployeService notificationEmployeService;
	@Autowired
	private DepartementService departementService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private GradeEmployeDao gradeEmployeDao;
	@Autowired
	private FonctionService fonctionService;
	@Autowired
	private EmolumentsService emolumentsService;
	@Autowired
	private RevenuService revenuService;
	@Autowired
	private NoteGeneraleService noteGeneraleService;
	@Autowired
	private FormationService formationService;
	@Autowired
	private PunitionEmployeService punitionEmployeService;
	@Autowired
	private PrixEmployeService prixEmployeService;
	@Autowired
	private RapportDeEvaluationService rapportDeEvaluationService;
	@Autowired
	private CongeService congeService;
	@Autowired
	private NotificationService notificationService;
	@Override
	public List<Employe> findAll() {
		return employeDao.findAll();
	}
	
	private boolean validate(String value, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(value);
		if (m.find() && m.group().equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Employe employe) {
		if ((!validate(employe.getFirstName(), "[a-zA-Z]*")) || (!validate(employe.getLastName(), "[a-zA-Z]*"))){
			return -1;
		} else if(!validate(employe.getEmail(),"[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
			return -2;
		}else {
		// departement de employe
		Departement dep = departementService.findByNom(employe.getDep().getNom());
		employe.setDep(dep);
		// donction de employe
		Fonction fct = fonctionService.findByLibelle(employe.getFonction().getLibelle());
		employe.setFonction(fct);
		//Solde Restantes Congé Exceptionnel
		employe.setSoldeRestantesCongeExceptionnel(10);
		//Dernier note
		employe.setDateDeProchainNote(DateUlils.getDateDeNote(employe.getDernierGrade().getDateDeAffectation()));
		employe.setDernierNote(null);
		employe.setDateProchainEvaluation(DateUlils.getDateEvaluationDeGrade(employe.getDernierGrade()));
		//dernier grade
		Date date = employe.getDernierGrade().getDateDeAffectation();
		GradeEmploye gradeEmploye = new GradeEmploye();
		gradeEmploye.setDoti(employe.getDoti());
		employe.setDateAvancementPrevue(null);
		//sup employe
		if(employe.getFonction().getLibelle().startsWith("chef département")) {
			employe.setSup(null);
		}else {
			employe.setSup(employeDao.findByDoti(dep.getChefdoti()));
		}
		Grade grade = gradeService.findByLibelle(employe.getDernierGrade().getGrade().getLibelle());

		//date prochaine evaluation
		employe.setDernierGrade(null);
		// save emploe
		employeDao.save(employe);
		//grade employe
		gradeEmploye.setDateDeAffectation(date);
		gradeEmploye.setGrade(grade);
		gradeEmploye.setEtat("traité");
		employe.setDernierGrade(gradeEmploye);
		//save grade employe
		gradeEmployeDao.save(gradeEmploye);
		// salaire employe
		SalaireEmploye salaireEmploye = new SalaireEmploye();
		salaireEmploye.setEmploye(employe);
		salaireEmploye.setSalaireNet(getSalaireParGrade(gradeEmploye.getGrade()));
		if(employe.getEnfants() == null) {
			employe.setEnfants(0);
		}
					//emoulument
		Double som = 0.0;
		salaireEmploye.setAllocationDeEncadrement(emolumentsService.findByLibelle("allocationDeEncadrement"));
		som += salaireEmploye.getAllocationDeEncadrement().getMontant();
		salaireEmploye.setAllocationDeEnseignement(emolumentsService.findByLibelle("allocationDeEnseignement"));
		som += salaireEmploye.getAllocationDeEnseignement().getMontant();
		salaireEmploye.setIdemDeLaResidence(emolumentsService.findByLibelle("idemDeLaResidence"));
		som += salaireEmploye.getIdemDeLaResidence().getMontant();
		salaireEmploye.setIdemFamialieleMarocaine(emolumentsService.findByLibelle("idemFamialieleMarocaine"));
		som += salaireEmploye.getIdemFamialieleMarocaine().getMontant() * employe.getEnfants();
					//revenu
		salaireEmploye.setAssuranceMaladieObligatoire(revenuService.findByLibelle("assuranceMaladieObligatoire"));
		som -= salaireEmploye.getAssuranceMaladieObligatoire().getMontant();
		salaireEmploye.setImpotSurLeRevenu(revenuService.findByLibelle("impotSurLeRevenu"));
		som -= salaireEmploye.getImpotSurLeRevenu().getMontant();
		salaireEmploye.setMutuelleCaisseRetraitEtDeces(revenuService.findByLibelle("mutuelleCaisseRetraitEtDeces"));
		som -= salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant();
		salaireEmploye.setCaisseMarocaineDeretrait(revenuService.findByLibelle("caisseMarocaineDeretrait"));
		som -= salaireEmploye.getCaisseMarocaineDeretrait().getMontant();
		salaireEmploye.setMonatntModifie(salaireEmploye.getSalaireNet() + som);
		// save salaire employe
		salaireEmployeService.save(salaireEmploye);
		// save employe
		employeDao.save(employe);
		Notification notification = notificationService.findByType("save");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "save employe");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}
	}
	
	@Override
	public int update(Employe employe) {
		Employe loadedemploye = findByid(employe.getId());
		//employe.setDoti(employe.getCin() + 19);
			// departement de employe
			Departement dep = departementService.findByNom(employe.getDep().getNom());
			employe.setDep(dep);
			// donction de employe
			Fonction fct = fonctionService.findByLibelle(employe.getFonction().getLibelle());
			employe.setFonction(fct);
			// salaire employe
			SalaireEmploye salaireEmploye = salaireEmployeService.findByEmployeDoti(employe.getDoti());
			salaireEmploye.setEmploye(employe);
			if(employe.getEnfants() == null) {
				employe.setEnfants(0);
			}
						//emoulument
			Double som = 0.0;
			salaireEmploye.setAllocationDeEncadrement(emolumentsService.findByLibelle("allocationDeEncadrement"));
			som += salaireEmploye.getAllocationDeEncadrement().getMontant();
			salaireEmploye.setAllocationDeEnseignement(emolumentsService.findByLibelle("allocationDeEnseignement"));
			som += salaireEmploye.getAllocationDeEnseignement().getMontant();
			salaireEmploye.setIdemDeLaResidence(emolumentsService.findByLibelle("idemDeLaResidence"));
			som += salaireEmploye.getIdemDeLaResidence().getMontant();
			salaireEmploye.setIdemFamialieleMarocaine(emolumentsService.findByLibelle("idemFamialieleMarocaine"));
			som += salaireEmploye.getIdemFamialieleMarocaine().getMontant() * employe.getEnfants();
						//revenu
			salaireEmploye.setAssuranceMaladieObligatoire(revenuService.findByLibelle("assuranceMaladieObligatoire"));
			som -= salaireEmploye.getAssuranceMaladieObligatoire().getMontant();
			salaireEmploye.setImpotSurLeRevenu(revenuService.findByLibelle("impotSurLeRevenu"));
			som -= salaireEmploye.getImpotSurLeRevenu().getMontant();
			salaireEmploye.setMutuelleCaisseRetraitEtDeces(revenuService.findByLibelle("mutuelleCaisseRetraitEtDeces"));
			som -= salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant();
			salaireEmploye.setCaisseMarocaineDeretrait(revenuService.findByLibelle("caisseMarocaineDeretrait"));
			som -= salaireEmploye.getCaisseMarocaineDeretrait().getMontant();
			salaireEmploye.setMonatntModifie(salaireEmploye.getSalaireNet() + som);
			// save salaire employe
			salaireEmployeService.save(salaireEmploye);
			if(employe.getFonction().getLibelle().startsWith("chef département")) {
				employe.setSup(null);
			}else {
				employe.setSup(employeDao.findByDoti(dep.getChefdoti()));
			}			
			employe.setSup(findByDoti(dep.getChefdoti()));
			employeDao.save(employe);
			Notification notification = notificationService.findByType("update");
			NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "update employe");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}

	@Override
	public Employe findByid(Long id) {
		if (employeDao.findById(id).isPresent()) {
			return employeDao.findById(id).get();
		} else
			return null;
	}

	@Override
	public int deleteById(Long id) {
		Employe employe = findByid(id);
		if (employe.getSup() == null) {
			return -2;
		} else {
			Notification notification = notificationService.findByType("delete");
			NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe , new Date(), "delete employe");
			notificationEmployeService.save(notificationEmploye);
			List<GradeEmploye> gradeEmployes = gradeEmployeService.findByDoti(employe.getDoti());
			gradeEmployes.forEach(grade -> {
				gradeEmployeService.deleteById(grade.getId());
//				rapportDeEvaluationService.deleteById(rapportDeEvaluationService.findByNouveauGradeIdAndEmployeDoti(grade.getId(), employe.getDoti()).getId());
			});
			salaireEmployeService.deleteById(salaireEmployeService.findByEmployeDoti(employe.getDoti()).getId());
			List<PrixEmploye> prixEmployes = prixEmployeService.findByEmployeDoti(employe.getDoti());
			for (PrixEmploye prixEmploye : prixEmployes) {
				prixEmployeService.deleteById(prixEmploye.getId());
			}
			List<Formation> formations = formationService.findByemployeDoti(employe.getDoti());
			for (Formation formation : formations) {
				formationService.deleteById(formation.getId());
			}
			List<PunitionEmploye> punitionEmployes = punitionEmployeService.findByEmployeDoti(employe.getDoti());
			for (PunitionEmploye punitionEmploye : punitionEmployes) {
				punitionEmployeService.deleteById(punitionEmploye.getId());
			}
			List<NoteGeneralDeAnnee> noteGeneralDeAnnees = noteGeneraleService.findByEmployeDoti(employe.getDoti());
			for (NoteGeneralDeAnnee noteGeneralDeAnnee : noteGeneralDeAnnees) {
				noteGeneraleService.deleteById(noteGeneralDeAnnee.getId());
			}
			List<Congé> congés = congeService.findByEmployeDoti(employe.getDoti());
			for (Congé congé : congés) {
				congeService.deleteById(congé.getId());
			}
			employeDao.deleteById(id);
			if (findByid(id) == null) {
				return 1;
			} else
				return -1;
		}
	}

	@Override
	public int nombreDesEmployes() {
		return findAll().size();
	}

	@Override
	public int nombreDesEmployesParDepartements(String nomDepartement) {
		List<Employe> employes = findAll();
		List<Employe> resultat = new ArrayList<Employe>();
		employes.forEach(e -> {
			if (e.getDep().getNom().equals(nomDepartement))
				resultat.add(e);
		});
		return resultat.size();
	}

	@Override
	public int nombreDesEmployesParAnneeDeEntré(Integer annee) {
		List<Employe> employes = findAll();
		List<Employe> resultat = new ArrayList<Employe>();
		employes.forEach(e -> {
			if (DateUlils.getYear(e.getDateEntree()) == annee)
				resultat.add(e);
		});
		return resultat.size();
	}

	@Override
	public List<Employe> EmployesParAnneeDeEntré(Integer annee) {
		List<Employe> employes = findAll();
		List<Employe> resultat = new ArrayList<Employe>();
		employes.forEach(e -> {
			if (DateUlils.getYear(e.getDateEntree()) == annee)
				resultat.add(e);
		});
		return resultat;
	}

	@Override
	public Double MoyenDeSalaireParAnnee(int annee) {
		Double SomMontant = 0.0;
		List<Employe> resultat = new ArrayList<Employe>();
		List<Employe> employes = new ArrayList<Employe>();
		for (int i = 2010; i < annee; i++) {
			Integer ans = Integer.valueOf(i);
			employes.addAll(EmployesParAnneeDeEntré(ans));
		}
		for (Employe employe : employes) {
			SalaireEmploye salaireEmploye = salaireEmployeService.findByid(employe.getId());
			SomMontant += salaireEmploye.getSalaireNet();
		}
		return SomMontant / resultat.size();
	}

	@Override
	public Employe findByCin(String cin) {
		return employeDao.findByCin(cin);
	}

	@Override
	public Employe findByDoti(String doti) {
		return employeDao.findByDoti(doti);
	}

	@Override
	public Employe findByEmail(String email) {
		return employeDao.findByEmail(email);
	}

	@Override
	public List<Employe> findBySupId(Long id) {
		return employeDao.findBySupId(id);
	}

	@Override
	public List<Employe> findByDateAvancementPrevue(Date dateAvancementPrevue) {
		return employeDao.findByDateAvancementPrevue(dateAvancementPrevue);
	}

	@Override
	public List<Employe> findByDateDeProchainNote(Date dateDeProchainNote) {
		return employeDao.findByDateDeProchainNote(dateDeProchainNote);
	}

	@Override
	public List<Employe> findByDateProchainEvaluation(Date dateProchainEvaluation) {
		return employeDao.findByDateProchainEvaluation(dateProchainEvaluation);
	}

	@Override
	public List<Employe> findLesEmployeAyantEvaluationAujourdHui() {
		Date dateAujourdHui = new Date();
		List<Employe> employes = findAll();
		Notification notification = new Notification("evaluation est ahjourd'ui");
		List<Employe> resultat = new ArrayList<Employe>();
		for (Employe employe : employes) {
			if (employe.getDateProchainEvaluation() == dateAujourdHui) {
				resultat.add(employe);
				NotificationEmploye notificationEmploye = new NotificationEmploye();
				notificationEmploye.setDateDeNotification(dateAujourdHui);
				notificationEmploye.setEmploye(employe);
				notificationEmploye.setNotification(notification);
				notificationEmploye.setLibelle("non lus");
			}
		}
		return resultat;
	}

	@Override
	public List<Employe> findLesEmployeAyantAvancementAujourdHui() {
		Date dateAujourdHui = new Date();
		List<Employe> employes = findAll();
		Notification notification = new Notification("avancement est Anjourd'hui");
		List<Employe> resultat = new ArrayList<Employe>();
		for (Employe employe : employes) {
			if (employe.getDateProchainEvaluation() != null && DateUlils.VerifieDate(employe.getDateProchainEvaluation())) {
				resultat.add(employe);
				//notification
				NotificationEmploye notificationEmploye = new NotificationEmploye();
				notificationEmploye.setDateDeNotification(dateAujourdHui);
				notificationEmploye.setEmploye(employe);
				notificationEmploye.setNotification(notification);
				notificationEmploye.setLibelle("non lus");
				//grade emplye
				GradeEmploye gradeEmploye = new GradeEmploye();
				gradeEmploye.setEtat("en traitement");
				gradeEmploye.setDoti(employe.getDoti());
				gradeEmploye.setGrade(gradeEmploye.getGrade());
				gradeEmploye.setDateDeAffectation(null);
				//rapport evaluation
				RapportDeEvaluation rapportDeEvaluation = new RapportDeEvaluation();
				rapportDeEvaluation.setEmploye(employe);
							// notes 
			rapportDeEvaluation.setNoteGenerale(noteGeneraleService.findNoteDeEmploye(employe));
							//formation
			rapportDeEvaluation.setFormation(formationService.findFormationDeEmploye(employe));
							//Punition
			rapportDeEvaluation.setPunition(punitionEmployeService.findPunitionDeEmploye(employe));
							//prix
			rapportDeEvaluation.setPrix(prixEmployeService.findPrixDeEmploye(employe));
							//moyen
			rapportDeEvaluation.setMoyen(getMoyenNote(noteGeneraleService.findNoteDeEmploye(employe)));
			//moyen
			rapportDeEvaluation.setMention(DateUlils.GetMention(rapportDeEvaluation.getMoyen()));
			rapportDeEvaluationService.save(rapportDeEvaluation);
			// date prochaine evaluation
			employe.setDateProchainEvaluation(null);
			}
		}
		return resultat;
	}
	//getMoyenNote
public Double getMoyenNote(List<NoteGeneralDeAnnee> notes) {
	Double somme = null;
	for (NoteGeneralDeAnnee noteGeneralDeAnnee : notes) {
		somme += noteGeneralDeAnnee.getMoyenGeneral();
	}
	return somme/notes.size();
}
	@Override
	public List<Employe> findLesEmployeAyantLaNoteGeneraleAujourdHui() {
		Date dateAujourdHui = new Date();
		List<Employe> employes = findAll();
		List<Employe> resultat = new ArrayList<Employe>();
		for (Employe employe : employes) {
			if (DateUlils.verifierDateSup(employe.getDateDeProchainNote(), dateAujourdHui)) {
				resultat.add(employe);
				NotificationEmploye notificationEmploye = new NotificationEmploye();
				notificationEmploye.setDateDeNotification(dateAujourdHui);
				notificationEmploye.setEmploye(employe);
				notificationEmploye.setNotification(notificationService.findByType("note aujourd'hui"));
				notificationEmploye.setLibelle("non lus");
			}
		}
		return resultat;
	}

	@Override
	public List<Employe> findBySoldeRestantesCongeExceptionnel(Integer soldeRestantesCongéExceptionnel) {
		return employeDao.findBySoldeRestantesCongeExceptionnel(soldeRestantesCongéExceptionnel);
	}

	@Override
	public List<Employe> findByDepNom(String nomDepartement) {
		return employeDao.findByDepNom(nomDepartement);
	}

	@Override
	public List<Employe> findByDernierGradeGradeLibelle(String libelle) {
		return employeDao.findByDernierGradeGradeLibelle(libelle);
	}
	//get salaire par grade
	public static Double getSalaireParGrade(Grade grade) {
		Double salaire = null;
		switch (grade.getLibelle()) {
		case "grade1":
			salaire = 4000.00;
			break;
		case "grade2":
			salaire = 4500.00;
			break;
		case "grade3":
			salaire = 5000.00;
			break;
		case "grade4":
			salaire = 5500.00;
			break;
		case "grade5":
			salaire = 6000.00;
			break;
		case "grade6":
			salaire = 6500.00;
			break;
		case "grade7":
			salaire = 7000.00;
			break;
		case "grade8":
			salaire = 7500.00;
			break;
		case "grade9":
			salaire = 8000.00;
			break;
		case "grade10":
			salaire = 8500.00;
			break;
		}
		return salaire;
	}
	
	//liste Des Employe De grade Pdf
		public int listeDesEmployeDeGradePdf(ArrayList<Employe> employes) throws DocumentException, FileNotFoundException {
			String grade= null;
			Employe employe2 = new Employe();
			for (Employe employe : employes) {
				grade = employe.getDernierGrade().getGrade().getLibelle();
				employe2= employe;
			}
				Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(grade + "listeEmployes.pdf")); 
			
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
			Paragraph p1 = new Paragraph("\n\t liste des employes de departement :" + grade + " \n\r\n", font);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);

		    
		    PdfPTable table = new PdfPTable(9); // 3 columns.

		    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
		    PdfPCell cell2 = new PdfPCell(new Paragraph("cin"));
		    PdfPCell cell3 = new PdfPCell(new Paragraph("doti"));
		    PdfPCell cell4 = new PdfPCell(new Paragraph("email"));
		    PdfPCell cell5 = new PdfPCell(new Paragraph("adress"));
		    PdfPCell cell6 = new PdfPCell(new Paragraph("tel"));
		    PdfPCell cell7 = new PdfPCell(new Paragraph("departement"));
		    PdfPCell cell8 = new PdfPCell(new Paragraph("fonction"));
		    PdfPCell cell9 = new PdfPCell(new Paragraph("grade"));
		    table.addCell(cell1);
		    table.addCell(cell2);
		    table.addCell(cell3);
		    table.addCell(cell4);
		    table.addCell(cell5);
		    table.addCell(cell6);
		    table.addCell(cell7);
		    table.addCell(cell8);
		    table.addCell(cell9);
		    for (Employe employe : employes) {
			    PdfPCell cell10 = new PdfPCell(new Paragraph(employe.getFirstName() + employe.getLastName()));
			    PdfPCell cell11 = new PdfPCell(new Paragraph(employe.getCin().toString()));
			    PdfPCell cell12 = new PdfPCell(new Paragraph(employe.getDoti().toString()));
			    PdfPCell cell13 = new PdfPCell(new Paragraph(employe.getEmail()));
			    PdfPCell cell14 = new PdfPCell(new Paragraph(employe.getAdresse()));
			    PdfPCell cell15 = new PdfPCell(new Paragraph(employe.getTel().toString()));
			    PdfPCell cell16 = new PdfPCell(new Paragraph(employe.getDep().getNom()));
			    PdfPCell cell17 = new PdfPCell(new Paragraph(employe.getFonction().getLibelle()));
			    PdfPCell cell18 = new PdfPCell(new Paragraph(employe.getDernierGrade().getGrade().getLibelle()));		
			    table.addCell(cell10);
			    table.addCell(cell11);
			    table.addCell(cell12);
			    table.addCell(cell13);
			    table.addCell(cell14);
			    table.addCell(cell15);
			    table.addCell(cell16);
			    table.addCell(cell17);
			    table.addCell(cell18);
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
			Notification notification = notificationService.findByType("imprimer");
			NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe2 , new Date(), "imprimer grade employe");
			notificationEmployeService.save(notificationEmploye);
			return 1;
		}	

	
	
	
	
	//liste Des Employe De Departement Pdf
	public int listeDesEmployeDeDepartementPdf(ArrayList<Employe> employes) throws DocumentException, FileNotFoundException {
		String depatrement= null;
		Employe employe2 = new Employe();
		for (Employe employe : employes) {
			depatrement = employe.getDep().getNom();
			employe2 = employe;
		}
			Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(depatrement + "listeEmployes.pdf")); 
		
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
		Paragraph p1 = new Paragraph("\n\t liste des employes de departement :" + depatrement + " \n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

	    
	    PdfPTable table = new PdfPTable(9); // 3 columns.

	    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
	    PdfPCell cell2 = new PdfPCell(new Paragraph("cin"));
	    PdfPCell cell3 = new PdfPCell(new Paragraph("doti"));
	    PdfPCell cell4 = new PdfPCell(new Paragraph("email"));
	    PdfPCell cell5 = new PdfPCell(new Paragraph("adress"));
	    PdfPCell cell6 = new PdfPCell(new Paragraph("tel"));
	    PdfPCell cell7 = new PdfPCell(new Paragraph("departement"));
	    PdfPCell cell8 = new PdfPCell(new Paragraph("fonction"));
	    PdfPCell cell9 = new PdfPCell(new Paragraph("grade"));
	    table.addCell(cell1);
	    table.addCell(cell2);
	    table.addCell(cell3);
	    table.addCell(cell4);
	    table.addCell(cell5);
	    table.addCell(cell6);
	    table.addCell(cell7);
	    table.addCell(cell8);
	    table.addCell(cell9);
	    for (Employe employe : employes) {
		    PdfPCell cell10 = new PdfPCell(new Paragraph(employe.getFirstName() + employe.getLastName()));
		    PdfPCell cell11 = new PdfPCell(new Paragraph(employe.getCin().toString()));
		    PdfPCell cell12 = new PdfPCell(new Paragraph(employe.getDoti().toString()));
		    PdfPCell cell13 = new PdfPCell(new Paragraph(employe.getEmail()));
		    PdfPCell cell14 = new PdfPCell(new Paragraph(employe.getAdresse()));
		    PdfPCell cell15 = new PdfPCell(new Paragraph(employe.getTel().toString()));
		    PdfPCell cell16 = new PdfPCell(new Paragraph(employe.getDep().getNom()));
		    PdfPCell cell17 = new PdfPCell(new Paragraph(employe.getFonction().getLibelle()));
		    PdfPCell cell18 = new PdfPCell(new Paragraph(employe.getDernierGrade().getGrade().getLibelle()));		
		    table.addCell(cell10);
		    table.addCell(cell11);
		    table.addCell(cell12);
		    table.addCell(cell13);
		    table.addCell(cell14);
		    table.addCell(cell15);
		    table.addCell(cell16);
		    table.addCell(cell17);
		    table.addCell(cell18);
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
		Notification notification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification,employe2 , new Date(), "imprimer employe d'une departement");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}	
	//liste des employes
	public int listeDesEmployePdf() throws DocumentException, FileNotFoundException {
		List<Employe> employes = findAll();
			Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("listeEmployes.pdf")); 
		
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
		Paragraph p1 = new Paragraph("\n\t liste des employes\n\r\n", font);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);

	    
	    PdfPTable table = new PdfPTable(9); // 3 columns.

	    PdfPCell cell1 = new PdfPCell(new Paragraph("fullName"));
	    PdfPCell cell2 = new PdfPCell(new Paragraph("cin"));
	    PdfPCell cell3 = new PdfPCell(new Paragraph("doti"));
	    PdfPCell cell4 = new PdfPCell(new Paragraph("email"));
	    PdfPCell cell5 = new PdfPCell(new Paragraph("adress"));
	    PdfPCell cell6 = new PdfPCell(new Paragraph("tel"));
	    PdfPCell cell7 = new PdfPCell(new Paragraph("departement"));
	    PdfPCell cell8 = new PdfPCell(new Paragraph("fonction"));
	    PdfPCell cell9 = new PdfPCell(new Paragraph("grade"));
	    table.addCell(cell1);
	    table.addCell(cell2);
	    table.addCell(cell3);
	    table.addCell(cell4);
	    table.addCell(cell5);
	    table.addCell(cell6);
	    table.addCell(cell7);
	    table.addCell(cell8);
	    table.addCell(cell9);
	    for (Employe employe : employes) {
		    PdfPCell cell10 = new PdfPCell(new Paragraph(employe.getFirstName() + employe.getLastName()));
		    PdfPCell cell11 = new PdfPCell(new Paragraph(employe.getCin().toString()));
		    PdfPCell cell12 = new PdfPCell(new Paragraph(employe.getDoti().toString()));
		    PdfPCell cell13 = new PdfPCell(new Paragraph(employe.getEmail()));
		    PdfPCell cell14 = new PdfPCell(new Paragraph(employe.getAdresse()));
		    PdfPCell cell15 = new PdfPCell(new Paragraph(employe.getTel().toString()));
		    PdfPCell cell16 = new PdfPCell(new Paragraph(employe.getDep().getNom()));
		    PdfPCell cell17 = new PdfPCell(new Paragraph(employe.getFonction().getLibelle()));
		    PdfPCell cell18 = new PdfPCell(new Paragraph(employe.getDernierGrade().getGrade().getLibelle()));		
		    table.addCell(cell10);
		    table.addCell(cell11);
		    table.addCell(cell12);
		    table.addCell(cell13);
		    table.addCell(cell14);
		    table.addCell(cell15);
		    table.addCell(cell16);
		    table.addCell(cell17);
		    table.addCell(cell18);
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
		Notification notification = notificationService.findByType("imprimer");
		NotificationEmploye notificationEmploye = new NotificationEmploye(notification,null , new Date(), "imprimer la liste employe");
		notificationEmployeService.save(notificationEmploye);
		return 1;
	}
	public List<Employe> getProchaineAvancement(){
		Date date = new Date();
		System.out.println("ha month" + (DateUlils.getMonth(date) + 1));
		System.out.println("ha year" + DateUlils.getYear(date));
		List<Employe> employes = findAll();
		List<Employe> resultat= new ArrayList<Employe>();
        for (Employe employe : employes) {
        	if(employe.getDateAvancementPrevue() != null) {
    			if(DateUlils.verifierDateSup(DateUlils.getDateDebutOfMonth(DateUlils.getYear(date), (DateUlils.getMonth(date))), employe.getDateAvancementPrevue())) {
    		resultat.add(employe);
    		System.out.println("ana hna");
        	}        		
        	}
        }
        return resultat;
	}
}