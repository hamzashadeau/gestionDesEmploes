package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Congé;
import com.example.stock.service.facade.CongeService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/conge/")
public class CongeRest {
@Autowired
private CongeService congeService;

@GetMapping("AutoRestSoldeCongeEmplye")
public int AutoRestSoldeCongeEmplye() {
	return congeService.AutoRestSoldeCongeEmplye();
}

@GetMapping("resetSoldeCongéEmploye")
public void resetSoldeCongéEmploye() {
	 congeService.resetSoldeCongéEmploye();
}

@GetMapping("findListeCertificatByAnnee")
public List<Congé> findListeCertificatByAnnee() {
	return congeService.findListeCertificatByAnnee();
}

@GetMapping("findCongeCertificat")
public List<Congé> findCongeCertificat() {
	return congeService.findCongeCertificat();
}
@GetMapping("findByEmployeDotiAndCongeeLibelle/matricule/{matricule}/libelle/{libelle}")
public List<Congé> findByEmployeDotiAndCongeeLibelle(@PathVariable String matricule,@PathVariable String libelle) {
	return congeService.findByEmployeDotiAndCongeeLibelle(matricule, libelle);
}
@GetMapping("findByCongeeLibelleAndDateDeDebut/libelle/{libelle}/date/{date}")
public List<Congé> findByCongeeLibelleAndDateDeDebut(@PathVariable String libelle,@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date) {
	return congeService.findByCongeeLibelleAndDateDeDebut(libelle, date);
}
@GetMapping("findCongeByAnne/annee/{annee}/type/{type}")
public List<Congé> findCongeByAnne(@PathVariable Integer annee,@PathVariable String type) {
	return congeService.findCongeByAnne(annee, type);
}
@PostMapping("update")
public int update(@RequestBody Congé congé) {
	return congeService.update(congé);
}
@PostMapping("listeDesCongéPdf")
public int listeDesCongéPdf(@RequestBody List<Congé> conges) throws DocumentException, FileNotFoundException {
	return congeService.listeDesCongéPdf(conges);
}
@GetMapping("findAll")
public List<Congé> findAll() {
	return congeService.findAll();
}
@GetMapping("findCongeCertificatLongDuree")
public List<Congé> findCongeCertificatLongDuree() {
	return congeService.findByCongeeLibelle("certificat long duree");
}
@GetMapping("findCongeCertificatcourtDuree")
public List<Congé> findCongeCertificatcourtDuree() {
	return congeService.findByCongeeLibelle("certificat court duree");
}
@GetMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return congeService.deleteById(id);
}

@GetMapping("findByCongeeLibelle/libelle/{libelle}")
public List<Congé> findByCongeeLibelle(@PathVariable String libelle) {
	return congeService.findByCongeeLibelle(libelle);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<Congé> findByEmployeEmail(@PathVariable String email) {
	return congeService.findByEmployeEmail(email);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<Congé> findByEmployeDoti(@PathVariable String doti) {
	return congeService.findByEmployeDoti(doti);
}


@PostMapping("save")
public int save(@RequestBody Congé conge) {
	return congeService.save(conge);
}

@GetMapping("findByid/id/{id}")
public Congé findByid(@PathVariable Long id) {
	return congeService.findByid(id);
}
}
