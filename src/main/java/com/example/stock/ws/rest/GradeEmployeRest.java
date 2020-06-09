package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Employe;
import com.example.stock.bean.GradeEmploye;
import com.example.stock.service.facade.GradeEmployeService;
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/GradeEmploye/")
public class GradeEmployeRest {
@Autowired
private GradeEmployeService gradeService;

@GetMapping("getDateEvaluation")
public int getDateEvaluation() {
	return gradeService.getDateEvaluation();
}

@GetMapping("getDateAvancement")
public int getDateAvancement() {
	return gradeService.getDateAvancement();
}

@GetMapping("creeUnGradeNonTraite/doti/{doti}")
public int creeUnGradeNonTraite(@PathVariable String doti) {
	return gradeService.creeUnGradeNonTraite(doti);
}

@PostMapping("listeDeGradeDeEmployePdf")
public int listeDeGradeDeEmployePdf(@RequestBody List<GradeEmploye> grades) throws DocumentException, FileNotFoundException {
	return gradeService.listeDeGradeDeEmployePdf(grades);
}

@PostMapping("update")
public int update(@RequestBody GradeEmploye gradeEmploye) {
	return gradeService.update(gradeEmploye);
}

@PostMapping("accepterUnGrade")
public int accepterUnGrade(@RequestBody GradeEmploye gradeEmploye) {
	return gradeService.accepterUnGrade(gradeEmploye);
}

@GetMapping("findGradeNonTraite")
public List<GradeEmploye> findGradeNonTraite() {
	return gradeService.findGradeNonTraite();
}

@GetMapping("findByEtat/etat/{etat}")
public List<GradeEmploye> findByEtat(@PathVariable String etat) {
	return gradeService.findByEtat(etat);
}



@GetMapping("findByDoti/doti/{doti}")
public List<GradeEmploye> findByDoti(@PathVariable String doti) {
	return gradeService.findByDoti(doti);
}

@GetMapping("findByDateDeAffectation/dateAfectation/{dateAfectation}")
public List<GradeEmploye> findByDateDeAffectation(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date dateAfectation) {
	return gradeService.findByDateDeAffectation(dateAfectation);
}


@GetMapping("findByid/id/{id}")
public GradeEmploye findByid(@PathVariable Long id) {
	return gradeService.findByid(id);
}


@GetMapping("findAll")
public List<GradeEmploye> findAll() {
	return gradeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody GradeEmploye grade) {
	return gradeService.save(grade);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return gradeService.deleteById(id);
}
}
