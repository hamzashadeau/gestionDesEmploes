package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.service.facade.DemandeDeDocumentService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/demandeDeDocument/")
public class DemandeDeDocumentRest {
@Autowired
private DemandeDeDocumentService demandeDeDocumentService;

@GetMapping("findDemandeNonTraite")
public List<DemaneDeDocument> findDemandeNonTraite() {
	return findByEtat("non traité");
}

@GetMapping("hellowordl")
public String hellowordl() throws DocumentException, FileNotFoundException {
	return demandeDeDocumentService.hellowordl();
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
public List<DemaneDeDocument> findByEmployeDoti(@PathVariable Integer doti) {
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
