package com.example.stock.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.transform.TransformerException;

import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DemaneDeDocumentDao;
import com.example.stock.Dao.EmployeDao;
import com.example.stock.Dao.TypeDocumentDao;
import com.example.stock.Utilis.HashUtil;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.bean.SalaireEmploye;
import com.example.stock.bean.TypeDocument;
import com.example.stock.service.facade.DemandeDeDocumentService;
import com.example.stock.service.facade.EmployeService;
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

@Override
public int save(DemaneDeDocument demaneDeDocument) {
	Employe employe = employeService.findByDoti(demaneDeDocument.getEmploye().getDoti());
	TypeDocument typeDocument = typeDocumentDao.findByLibelle(demaneDeDocument.getTypeDeDocument().getLibelle());
	if(demaneDeDocument.getId()!= null) {
return -1;
}else if(employe == null) {
	return -2;
} else if(typeDocument == null) {
	return -3;
} else {
	demaneDeDocument.setTypeDeDocument(typeDocument);
	demaneDeDocument.setEmploye(employe);
	demaneDeDocument.setDateDemande(new Date());
	demaneDeDocument.setEtat("non traité");
	demaneDeDocumentDao.save(demaneDeDocument);
		return 1;
}
	}
@Override
public int update(DemaneDeDocument demaneDeDocument) {
	Employe employe = employeService.findByDoti(demaneDeDocument.getEmploye().getDoti());
	TypeDocument typeDocument = typeDocumentDao.findByLibelle(demaneDeDocument.getTypeDeDocument().getLibelle());
	if(employe == null) {
		return -2;
	} else if(typeDocument == null) {
		return -3;
	} else {
		demaneDeDocument.setTypeDeDocument(typeDocument);
		employeDao.save(employe);
		demaneDeDocument.setEmploye(employe);
		demaneDeDocument.setDateDemande(new Date());
		demaneDeDocument.setEtat("non traité");
		demaneDeDocumentDao.save(demaneDeDocument);
			return 1;
	}
	}
//infoEmployePdf
public int infoEmployePdf(Employe employe) throws DocumentException, FileNotFoundException {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(employe.getFullName() + "Info.pdf")); 
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
	Paragraph p1 = new Paragraph("\n\t info Personnel", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

	Paragraph p3 = new Paragraph("\n info de base", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p3);
	
    Paragraph p = new Paragraph();
    p.add("\n\n*FullName: " + employe.getFullName()+     "                                  *cin:" + employe.getCin() + " \n" + 
    		"*doti:" + employe.getDoti() + "                                                               *email:" + employe.getEmail() + "\n" + 
    		"*gender: " + employe.getGender() + "                                               *situation Familial:" + employe.getSituationFamiliale()+ " \n" + 
    "*adresse: " + employe.getAdresse() + "                                       *enfant: " + employe.getEnfants() + "\n" + 
    		"*date de naissance:" + employe.getDateDeNaissance() + "                                *lieu de naissance : " + employe.getLieuDeNaissance() + "\n" + 
    		"*departement :" + employe.getDep().getNom() + "                                             * fonction : " + employe.getFonction().getLibelle() + "\n" + 
    		"*date entrée : " + employe.getDateEntree() + "");
    document.add(p);
//les dernier grade    
	Paragraph p5 = new Paragraph("\n les dernier Grade :\n\n", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p5);
    PdfPTable table = new PdfPTable(2); // 3 columns.
    PdfPCell cell1 = new PdfPCell(new Paragraph("libelle "));
    PdfPCell cell2 = new PdfPCell(new Paragraph("date affectation"));
    PdfPCell cell3 = new PdfPCell(new Paragraph(employe.getDernierGrade().getGrade().getLibelle()));
    PdfPCell cell4 = new PdfPCell(new Paragraph(employe.getDernierGrade().getDateDeAffectation().toString()));
    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
  document.add(table);

//les dernier note    
	Paragraph p6 = new Paragraph("\n les dernier note :\n\n", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p6);
    
    PdfPTable table1 = new PdfPTable(3); // 3 columns.

    PdfPCell cell5 = new PdfPCell(new Paragraph("moyen "));
    PdfPCell cell6 = new PdfPCell(new Paragraph("mention"));
    PdfPCell cell7 = new PdfPCell(new Paragraph("date "));
    PdfPCell cell8 = new PdfPCell(new Paragraph(employe.getDernierNote().getMoyenGeneral().toString()));
    PdfPCell cell9 = new PdfPCell(new Paragraph(employe.getDernierNote().getMention()));
    PdfPCell cell10 = new PdfPCell(new Paragraph(employe.getDernierNote().getDate().toString()));
   
    table1.addCell(cell5);
    table1.addCell(cell6);
    table1.addCell(cell7);
    table1.addCell(cell8);
    table1.addCell(cell9);
    table1.addCell(cell10);
 
    document.add(table1);
   
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




public int rapportPdf(RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream( rapportDeEvaluation.getEmploye().getFullName() + "Rapport.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t Rapport", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

	Paragraph p3 = new Paragraph("\n info de base", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p3);
	
    Paragraph p = new Paragraph();
    p.add("\n\n*FullName: " + rapportDeEvaluation.getEmploye().getFullName()+     "                                  *cin:" + rapportDeEvaluation.getEmploye().getCin() + " \n" + 
    		"*doti:" + rapportDeEvaluation.getEmploye().getDoti() + "                                                               *email:" + rapportDeEvaluation.getEmploye().getEmail() + "\n" + 
    		"*gender: " + rapportDeEvaluation.getEmploye().getGender() + "                                               *situation Familial:" + rapportDeEvaluation.getEmploye().getSituationFamiliale()+ " \n" + 
    "*adresse: " + rapportDeEvaluation.getEmploye().getAdresse() + "                                       *enfant: " + rapportDeEvaluation.getEmploye().getEnfants() + "\n" + 
    		"*date de naissance:" + rapportDeEvaluation.getEmploye().getDateDeNaissance() + "                                *lieu de naissance : " + rapportDeEvaluation.getEmploye().getLieuDeNaissance() + "\n" + 
    		"*departement :" + rapportDeEvaluation.getEmploye().getDep().getNom() + "                                             * fonction : " + rapportDeEvaluation.getEmploye().getFonction().getLibelle() + "\n" + 
    		"*date entrée : " + rapportDeEvaluation.getEmploye().getDateEntree() + "                                          Dernier Grade : " + rapportDeEvaluation.getEmploye().getDernierGrade().getGrade().getLibelle() + "  \n" + 
    		" date Affecation : " + rapportDeEvaluation.getEmploye().getDernierGrade().getDateDeAffectation() + "\n" + 
    		"");
    document.add(p);
//les notes    
	Paragraph p5 = new Paragraph("\n les notes :\n\n", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p5);
    
    PdfPTable table = new PdfPTable(3); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("moyen "));
    PdfPCell cell2 = new PdfPCell(new Paragraph("mention"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("date"));
    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
  for (NoteGeneralDeAnnee note : rapportDeEvaluation.getNoteGenerale()) {
	    PdfPCell cell4 = new PdfPCell(new Paragraph(note.getMoyenGeneral().toString()));
	    PdfPCell cell5 = new PdfPCell(new Paragraph(note.getMention()));
	    PdfPCell cell6 = new PdfPCell(new Paragraph(note.getDate().toString()));
	    table.addCell(cell4);
	    table.addCell(cell5);
	    table.addCell(cell6);	
}
  document.add(table);

 //les formation
	Paragraph p6 = new Paragraph("\n les formations :\n\n", font);
	p1.setAlignment(Element.ALIGN_LEFT);
	document.add(p6);
  
  PdfPTable table1 = new PdfPTable(6); // 3 columns.

  PdfPCell cell10 = new PdfPCell(new Paragraph("attestation "));
  PdfPCell cell11 = new PdfPCell(new Paragraph("domaine"));
  PdfPCell cell12 = new PdfPCell(new Paragraph("etablissement"));
  PdfPCell cell13 = new PdfPCell(new Paragraph("mention "));
  PdfPCell cell14 = new PdfPCell(new Paragraph("ville"));
  PdfPCell cell15 = new PdfPCell(new Paragraph("date"));
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
	    PdfPCell cell9 = new PdfPCell(new Paragraph(note.getAnnee().toString()));
	    table1.addCell(cell4);
	    table1.addCell(cell5);
	    table1.addCell(cell6);
	    table1.addCell(cell7);
	    table1.addCell(cell8);
	    table1.addCell(cell9);
}
   document.add(table1);
   
   
   //les prix
  	Paragraph p7 = new Paragraph("\n les prix :\n\n", font);
  	p1.setAlignment(Element.ALIGN_LEFT);
  	document.add(p7);
    
    PdfPTable table2 = new PdfPTable(2); // 3 columns.

    PdfPCell cell16 = new PdfPCell(new Paragraph("prix "));
    PdfPCell cell17 = new PdfPCell(new Paragraph("date Obtenation"));

    table2.addCell(cell16);
    table2.addCell(cell17);
  for (PrixEmploye note : rapportDeEvaluation.getPrix()) {
  	    PdfPCell cell4 = new PdfPCell(new Paragraph(note.getPrix().getLibelle()));
  	    PdfPCell cell5 = new PdfPCell(new Paragraph(note.getDateDeObtenation().toString()));

  	    table2.addCell(cell4);
  	    table2.addCell(cell5);
  }
     document.add(table2);
     
     //les punition
   	Paragraph p8 = new Paragraph("\n\n les punitions :\n\n", font);
   	p1.setAlignment(Element.ALIGN_LEFT);
   	document.add(p8);
     
     PdfPTable table3 = new PdfPTable(2); // 3 columns.

     PdfPCell cell18 = new PdfPCell(new Paragraph("punition "));
     PdfPCell cell19 = new PdfPCell(new Paragraph("date Obtenation"));

     table3.addCell(cell16);
     table3.addCell(cell17);
   for (PunitionEmploye note : rapportDeEvaluation.getPunition()) {
   	    PdfPCell cell4 = new PdfPCell(new Paragraph(note.getPunition().getLibelle()));
   	    PdfPCell cell5 = new PdfPCell(new Paragraph(note.getDateObtenation().toString()));

   	    table3.addCell(cell4);
   	    table3.addCell(cell5);
   }
      document.add(table3);
      
      //les resultats
     	Paragraph p9 = new Paragraph("\n les resultats :\n\n", font);
     	p1.setAlignment(Element.ALIGN_LEFT);
     	document.add(p9);
       
       PdfPTable table4 = new PdfPTable(3); // 3 columns.

       PdfPCell cell20 = new PdfPCell(new Paragraph("moyen "));
       PdfPCell cell21 = new PdfPCell(new Paragraph("mention"));
       PdfPCell cell22 = new PdfPCell(new Paragraph("remarque"));
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
   
   Paragraph p4 = new Paragraph( "\n \r\r\r\r signer :",f);
   p4.setAlignment(Element.ALIGN_LEFT);
   document.add(p4);

   Paragraph p20 = new Paragraph( "\n \r\r\r\r marakech  le :"+new  Date().toString(),f);
   p20.setAlignment(Element.ALIGN_LEFT);
   document.add(p20);
    document.close();
	return 1;
}
//attestation de salaire 
public int attestationDeSalaire(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException, TransformerException {
	Employe employe = demaneDeDocument.getEmploye();
	SalaireEmploye salaireEmploye = salaireEmployeService.findByEmployeDoti(employe.getDoti());
	
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(demaneDeDocument.getEmploye().getFullName() + "attestationDeSalaire" +demaneDeDocument.getEmploye().getDoti() + ".pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t ATTESTATION DE salaire " , font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);
	

    Paragraph p = new Paragraph();
    p.add(" \r\n" +"le service RH de  l'etablissement de science et de technique ATTESTE PAR LA PRESENTE QUE MR "+ employe.getFullName() +
    		"TITULAIRE  DE LA CIN N°" + employe.getCin()+  
    		" ,TRAVAILLE EN QUALITE dans " + 
    		"la faculté de science et technique DEPUIS LE"+  employe.getDateEntree()+",ET PERCOIT UN SALAIRE ANNUEL NET DE :"+salaireEmploye.getSalaireNet()+"DHS,DONT LE DETAIL EST LE SUIVANT :\r\n"
    				+"les emouluments:\n\r"
    				+ "Allocation De Encadrement:"+ salaireEmploye.getAllocationDeEncadrement().getMontant() +"\n\r"+
    				 "Idem De La Residence:"+ salaireEmploye.getIdemDeLaResidence().getMontant() +"\n\r"+
    				 "Idem Famialie le Marocaine:"+ salaireEmploye.getIdemFamialieleMarocaine() .getMontant()+"\n\r"+
    				 "Allocation De Enseignement:"+ salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant() +"\n\r"+
    				 "les revenues"+"\n\r"+
    				 "Assurance Maladie Obligatoire :"+ salaireEmploye.getAssuranceMaladieObligatoire().getMontant() +"\n\r"+
    				 "Caisse Marocaine De retrait:"+ salaireEmploye.getCaisseMarocaineDeretrait().getMontant() +"\n\r"+
    				 "Mutuelle Caisse Retrait Et Deces:"+ salaireEmploye.getMutuelleCaisseRetraitEtDeces().getMontant() +"\n\r"+
    				 "Impot Sur Le Revenu:"+ salaireEmploye.getImpotSurLeRevenu().getMontant());

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
	try {
		HashUtil.sendmail(demaneDeDocument.getEmploye().getEmail(), "attestation de travail", "bon reception","C:/Users/hp/eclipse-workspace/gestionDesEmploye/" + demaneDeDocument.getEmploye().getFullName() + "attestationDeSalaire" +demaneDeDocument.getEmploye().getDoti() + ".pdf");
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	demaneDeDocument.setEtat("traité");
	demaneDeDocumentDao.save(demaneDeDocument);
	return 1;
	}

//attestation de travail 
public int attestationDeTravail(DemaneDeDocument demaneDeDocument) throws DocumentException, FileNotFoundException, TransformerException {
	Employe employe = demaneDeDocument.getEmploye();
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(demaneDeDocument.getEmploye().getFullName()+"attestaionDeTravail" +demaneDeDocument.getEmploye().getDoti()+".pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t ATTESTATION DE Travail", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

  Paragraph p = new Paragraph();
  p.add("\n\nJe soussigné  le service RH de la faculté de la science et de technique atteste que :" +  "\n\r"+
"Mr (Mme) (Melle)    :  " + employe.getFullName() + "\n\r"+
"D.O.T.I     : " + employe.getDoti() + "\n\r"+
"CIN     : " + employe.getCin() +  "\n\r"+
"Date de recrutement  : " + employe.getDateEntree() + "\n\r"+ 
"Grade: " + employe.getDernierGrade().getGrade().getLibelle() + "\n\r"+
"fonction: " + employe.getFonction().getLibelle() + "\n\r"+
"Lieu de travail : la faculté de la science et de technique"+ "\n\r");
  p.setAlignment(Element.ALIGN_LEFT);

  document.add(p);

  Paragraph p2 = new Paragraph();
  p2.add(" \n\n \r\r\r\rLa présente attestation est délivrée a l’intéressé(e) pour servir et valoir ce que de droit \n \r\r\r\r Signé ");//no alignment
  p2.setAlignment(Element.ALIGN_CENTER);
  document.add(p2);

  Font f = new Font();
  f.setStyle(Font.BOLD);
  f.setSize(8);

  Paragraph p4 = new Paragraph( "\n \r\r\r\r marakech  le :"+new  Date().toString(),f);
  p4.setAlignment(Element.ALIGN_LEFT);
  document.add(p4);
	document.close();
	try {
		HashUtil.sendmail(demaneDeDocument.getEmploye().getEmail(), "attestation de travail", "bon reception","C:/Users/hp/eclipse-workspace/gestionDesEmploye/" + demaneDeDocument.getEmploye().getFullName() + "attestaionDeTravail" + demaneDeDocument.getEmploye().getDoti() + ".pdf");
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	demaneDeDocument.setEtat("traité");
	demaneDeDocumentDao.save(demaneDeDocument);
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
public List<DemaneDeDocument> findByEmployeDoti(String doti) {
	return demaneDeDocumentDao.findByEmployeDoti(doti);
}

@Override
public List<DemaneDeDocument> findByEtat(String etat) {
	return demaneDeDocumentDao.findByEtat(etat);
}
//liste des demandes
public int listeDesDemandePdf(List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException {
	String fullName = null;
	for (DemaneDeDocument demaneDeDocument : demandes) {
		fullName = demaneDeDocument.getEmploye().getFullName();
	}
		Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(fullName + "listedemandes.pdf")); 
	
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
	Paragraph p1 = new Paragraph("\n\t liste des demandes"+ fullName+"\n\r\n", font);
	p1.setAlignment(Element.ALIGN_CENTER);
	document.add(p1);

    
    PdfPTable table = new PdfPTable(4); // 3 columns.

    PdfPCell cell1 = new PdfPCell(new Paragraph("Nom Complet"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("Type de document"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("Date de demande"));
    PdfPCell cell4 = new PdfPCell(new Paragraph("Maniere de retrait"));
 
    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);

	for (DemaneDeDocument demaneDeDocument : demandes) {
	    PdfPCell cell10 = new PdfPCell(new Paragraph(demaneDeDocument.getEmploye().getFullName()));
	    PdfPCell cell11 = new PdfPCell(new Paragraph(demaneDeDocument.getTypeDeDocument().getLibelle()));
	    PdfPCell cell12 = new PdfPCell(new Paragraph(demaneDeDocument.getDateDemande().toString()));
	    PdfPCell cell13 = new PdfPCell(new Paragraph(demaneDeDocument.getManiereDeRetrait()));

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
