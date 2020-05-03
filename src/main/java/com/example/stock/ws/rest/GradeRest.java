package com.example.stock.ws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Grade;
import com.example.stock.service.facade.GradeService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/Grade/")
public class GradeRest {
@Autowired
private GradeService gradeService;

@GetMapping("findByLibelle/libelle/{libelle}")
public Grade findByLibelle(@PathVariable String libelle) {
	return gradeService.findByLibelle(libelle);
}


@GetMapping("findByid/id/{id}")
public Grade findByid(@PathVariable Long id) {
	return gradeService.findByid(id);
}


@GetMapping("findAll")
public List<Grade> findAll() {
	return gradeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Grade grade) {
	return gradeService.save(grade);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return gradeService.deleteById(id);
}
}
