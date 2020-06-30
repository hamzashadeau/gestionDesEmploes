package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.bean.Employe;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.DemandeDeDocumentService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/demandeDeDocument/")
public class DemandeDeDocumentRest {
@Autowired
private DemandeDeDocumentService demandeDeDocumentService;

@PostMapping("listeDesDemandesExcel")
public int listeDesDemandesExcel(@RequestBody List<DemaneDeDocument> demandes) {
	return demandeDeDocumentService.listeDesDemandesExcel(demandes);
}
@RequestMapping(value = "sendmail/id/{id}/email/{email}/subject/{subject}/content/{content}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public int sendmail(@PathVariable Long id,@PathVariable String email,@PathVariable String subject,@PathVariable String content,@RequestParam("file") MultipartFile  file )throws AddressException, MessagingException, IOException, TransformerException {
	System.out.println(file.getOriginalFilename());
	return demandeDeDocumentService.sendmail(id,email, subject, content, file);
}
@GetMapping("findByTypeDeDocumentLibelleAndEmployeDoti/libelle/{libelle}/doti/{doti}")
public List<DemaneDeDocument>  findByTypeDeDocumentLibelleAndEmployeDoti(@PathVariable String libelle,@PathVariable String doti) {
	return demandeDeDocumentService.findByTypeDeDocumentLibelleAndEmployeDoti(libelle, doti);
}
@PostMapping("update")
public int update(@RequestBody DemaneDeDocument demaneDeDocument) {
	return demandeDeDocumentService.update(demaneDeDocument);
}
@PostMapping("listeDesDemandePdf")
public int listeDesDemandePdf(@RequestBody List<DemaneDeDocument> demandes) throws DocumentException, FileNotFoundException {
	return demandeDeDocumentService.listeDesDemandePdf(demandes);
}
@PostMapping("infoEmployePdf")
public int infoEmployePdf(@RequestBody Employe employe) throws DocumentException, FileNotFoundException {
	return demandeDeDocumentService.infoEmployePdf(employe);
}

@PostMapping("attestationDeSalaire")
public int attestationDeSalaire(@RequestBody DemaneDeDocument demaneDeDocument) throws DocumentException, TransformerException, AddressException, MessagingException, IOException{
	return demandeDeDocumentService.attestationDeSalaire(demaneDeDocument);
}
@PostMapping("rapportPdf")
public int rapportPdf(@RequestBody RapportDeEvaluation rapportDeEvaluation) throws DocumentException, FileNotFoundException {
	return demandeDeDocumentService.rapportPdf(rapportDeEvaluation);
}
@GetMapping("findDemandeNonTraite")
public List<DemaneDeDocument> findDemandeNonTraite() {
	return findByEtat("non traité");
}
@GetMapping("findDemandeNonSigne")
public List<DemaneDeDocument> findDemandeNonSigne() {
	return findByEtat("non signe");
}
@PostMapping("attestationDeTravail")
public int attestationDeTravail(@RequestBody DemaneDeDocument demaneDeDocument) throws DocumentException, TransformerException, AddressException, MessagingException, IOException{
	return demandeDeDocumentService.attestationDeTravail(demaneDeDocument);
}

@GetMapping("findByEmployeId/id/{id}")
public List<DemaneDeDocument> findByEmployeId(@PathVariable Long id) {
	return demandeDeDocumentService.findByEmployeId(id);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<DemaneDeDocument> findByEmployeEmail(@PathVariable String email) {
	return demandeDeDocumentService.findByEmployeEmail(email);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<DemaneDeDocument> findByEmployeDoti(@PathVariable String doti) {
	return demandeDeDocumentService.findByEmployeDoti(doti);
}

@GetMapping("findByEtat/etat/{etat}")
public List<DemaneDeDocument> findByEtat(@PathVariable String etat) {
	return demandeDeDocumentService.findByEtat(etat);
}
@GetMapping("findAll")
public List<DemaneDeDocument> findAll() {
	return demandeDeDocumentService.findAll();
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return demandeDeDocumentService.deleteById(id);
}

@PostMapping("save")
public int save(@RequestBody DemaneDeDocument demandeDeDocument) {
	return demandeDeDocumentService.save(demandeDeDocument);
}

@GetMapping("findByid/id/{id}")
public DemaneDeDocument findByid(@PathVariable Long id) {
	return demandeDeDocumentService.findByid(id);
}
}
