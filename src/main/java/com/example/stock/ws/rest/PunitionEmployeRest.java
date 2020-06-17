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
import com.example.stock.bean.PunitionEmploye;
import com.example.stock.service.facade.PunitionEmployeService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/PunitionEmploye/")
public class PunitionEmployeRest {
@Autowired
private PunitionEmployeService punitionEmployeService;

@PostMapping("listeDesPunitionsExcel")
public int listeDesPunitionsExcel(@RequestBody List<PunitionEmploye> punitionEmployes) {
	return punitionEmployeService.listeDesPunitionsExcel(punitionEmployes);
}

@PostMapping("update")
public int update(@RequestBody PunitionEmploye punitionEmploye) {
	return punitionEmployeService.update(punitionEmploye);
}

@PostMapping("listeDespunitionsPdf")
public int listeDespunitionsPdf(@RequestBody ArrayList<PunitionEmploye> punitionEmployes)	throws DocumentException, FileNotFoundException {
	return punitionEmployeService.listeDespunitionsPdf(punitionEmployes);
}

@GetMapping("findPunitionDeEmploye/doti/{doti}")
public List<PunitionEmploye> findPunitionDeEmploye(@PathVariable String doti) {
	return punitionEmployeService.findPunitionDeEmploye(doti);
}

@GetMapping("findByPunitionType/type/{type}")
public List<PunitionEmploye> findByPunitionType(@PathVariable String type) {
	return punitionEmployeService.findByPunitionType(type);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<PunitionEmploye> findByEmployeDoti(@PathVariable String doti) {
	return punitionEmployeService.findByEmployeDoti(doti);
}

@GetMapping("findByEmployeId/id/{id}")
public List<PunitionEmploye> findByEmployeId(@PathVariable Long id) {
	return punitionEmployeService.findByEmployeId(id);
}


@GetMapping("findByEmployeEmail/email/{email}")
public List<PunitionEmploye> findByEmployeEmail(@PathVariable String email) {
	return punitionEmployeService.findByEmployeEmail(email);
}


@GetMapping("findByPunitionLibelle/libelle/{libelle}")
public List<PunitionEmploye> findByPunitionLibelle(@PathVariable String libelle) {
	return punitionEmployeService.findByPunitionLibelle(libelle);
}

@GetMapping("findByid/id/{id}")
public PunitionEmploye findByid(@PathVariable Long id) {
	return punitionEmployeService.findByid(id);
}

@GetMapping("findAll")
public List<PunitionEmploye> findAll() {
	return punitionEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody PunitionEmploye punitionEmploye) {
	return punitionEmployeService.save(punitionEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return punitionEmployeService.deleteById(id);
}
}
