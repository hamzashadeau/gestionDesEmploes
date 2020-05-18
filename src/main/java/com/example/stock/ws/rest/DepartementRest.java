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

import com.example.stock.bean.Departement;
import com.example.stock.service.facade.DepartementService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Departement/")
public class DepartementRest {
@Autowired
private DepartementService departementService;

@GetMapping("findByChefdoti/doti/{doti}")
public Departement findByChefdoti(@PathVariable Integer doti) {
	return departementService.findByChefdoti(doti);
}

@GetMapping("listedepartementPdf")
public int listedepartementPdf() throws DocumentException, FileNotFoundException {
	return departementService.listedepartementPdf();
}

@GetMapping("nombreDesDepartements")
public int nombreDesDepartements() {
	return departementService.nombreDesDepartements();
}

@GetMapping("findByid/id/{id}")
public Departement findByid(@PathVariable Long id) {
	return departementService.findByid(id);
}

@GetMapping("findByNomDepartemant/nomDepartement/{nomDepartement}")
public Departement findByNom(@PathVariable String nom) {
	return departementService.findByNom( nom);

}

@GetMapping("findBySupMatricule/doti/{matricule}")
public Departement findByChefMatricule(@PathVariable Integer matricule) {
	return departementService.findByChefdoti(matricule);
}

@GetMapping("findAll")
public List<Departement> findAll() {
	return departementService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Departement departement) {
	return departementService.save(departement);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return departementService.deleteById(id);
}
}
