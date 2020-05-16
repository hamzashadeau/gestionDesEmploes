package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public List<Congé> findByEmployeDoti(@PathVariable Integer doti) {
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
