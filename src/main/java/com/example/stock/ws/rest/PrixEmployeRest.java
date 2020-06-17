package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

import com.example.stock.bean.Employe;
import com.example.stock.bean.PrixEmploye;
import com.example.stock.service.facade.PrixEmployeService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/PrixEmploye/")
public class PrixEmployeRest {
@Autowired
private PrixEmployeService prixEmployeService;

@PostMapping("listeDesPrixExcel")
public int listeDesPrixExcel(@RequestBody List<PrixEmploye> prixEmployes) {
	return prixEmployeService.listeDesPrixExcel(prixEmployes);
}

@PostMapping("update")
public int update(@RequestBody PrixEmploye prixEmploye) {
	return prixEmployeService.update(prixEmploye);
}

@PostMapping("listeDesPrixPdf")
public int listeDesPrixPdf(@RequestBody ArrayList<PrixEmploye> prixEmployes) throws DocumentException, FileNotFoundException {
	return prixEmployeService.listeDesPrixPdf(prixEmployes);
}

@GetMapping("findPrixDeEmploye/doti/{doti}")
public List<PrixEmploye> findPrixDeEmploye(@PathVariable String doti) {
	return prixEmployeService.findPrixDeEmploye(doti);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<PrixEmploye> findByEmployeDoti(@PathVariable String doti) {
	return prixEmployeService.findByEmployeDoti(doti);
}

@GetMapping("findByPrixLibelle/libelle/{libelle}")
public List<PrixEmploye> findByPrixLibelle(@PathVariable String libelle) {
	return prixEmployeService.findByPrixLibelle(libelle);
}

@GetMapping("findByid/id/{id}")
public PrixEmploye findByid(@PathVariable Long id) {
	return prixEmployeService.findByid(id);
}

@GetMapping("findAll")
public List<PrixEmploye> findAll() {
	return prixEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody PrixEmploye prixEmploye) {
	return prixEmployeService.save(prixEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return prixEmployeService.deleteById(id);
}
}
