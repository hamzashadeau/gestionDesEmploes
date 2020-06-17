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
import com.example.stock.bean.Formation;
import com.example.stock.service.facade.FormationService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Formation/")
public class FormationRest {
@Autowired
private FormationService formationService;

@PostMapping("listeDesFormationsExcels")
public int listeDesFormationsExcel(@RequestBody List<Formation> formations) {
	return formationService.listeDesFormationsExcel(formations);
}

@PostMapping("update")
public int update(@RequestBody Formation formation) {
	return formationService.update(formation);
}

@PostMapping("listeDesFormationsPdf")
public int listeDesFormationsPdf(@RequestBody ArrayList<Formation> formations) throws DocumentException, FileNotFoundException {
	return formationService.listeDesFormationsPdf(formations);
}

@GetMapping("findFormationDeEmploye/doti/{doti}")
public List<Formation> findFormationDeEmploye(@PathVariable String doti) {
	return formationService.findFormationDeEmploye(doti);
}

@GetMapping("findByemployeId/id/{id}")
public List<Formation> findByemployeId(@PathVariable Long id) {
	return formationService.findByemployeId(id);
}

@GetMapping("findByemployeEmail/email/{email}")
public List<Formation> findByemployeEmail(@PathVariable String email) {
	return formationService.findByemployeEmail(email);
}

@GetMapping("findByemployeDoti/doti/{doti}")
public List<Formation> findByemployeDoti(@PathVariable String doti) {
	return formationService.findByemployeDoti(doti);
}


@GetMapping("findByid/id/{id}")
public Formation findByid(@PathVariable Long id) {
	return formationService.findByid(id);
}


@GetMapping("findAll")
public List<Formation> findAll() {
	return formationService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Formation formation) {
	return formationService.save(formation);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return formationService.deleteById(id);
}
}
