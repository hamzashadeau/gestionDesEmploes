package com.example.stock.ws.rest;

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

import com.example.stock.bean.GradeEmploye;
import com.example.stock.service.facade.GradeEmployeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/GradeEmploye/")
public class GradeEmployeRest {
@Autowired
private GradeEmployeService gradeService;


@GetMapping("findByEmployeid/id/{id}")
public List<GradeEmploye> findByEmployeid(@PathVariable Long id) {
	return gradeService.findByEmployeid(id);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<GradeEmploye> findByEmployeEmail(@PathVariable String email) {
	return gradeService.findByEmployeEmail(email);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<GradeEmploye> findByEmployeDoti(@PathVariable Integer doti) {
	return gradeService.findByEmployeDoti(doti);
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
