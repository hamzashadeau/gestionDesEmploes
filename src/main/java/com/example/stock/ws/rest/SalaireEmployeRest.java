package com.example.stock.ws.rest;

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

import com.example.stock.bean.SalaireEmploye;
import com.example.stock.service.facade.SalaireEmployeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/SalaireEmploye/")
public class SalaireEmployeRest {
@Autowired
private SalaireEmployeService salaireEmployeService;

@GetMapping("findByEmployeDoti/doti/{doti}")
public SalaireEmploye findByEmployeDoti(@PathVariable String doti) {
	return salaireEmployeService.findByEmployeDoti(doti);
}
@GetMapping("findBySalaireNet/montantDeBase/{montantDeBase}")
public List<SalaireEmploye> findBySalaireNet(@PathVariable Double montantDeBase) {
	return salaireEmployeService.findBySalaireNet(montantDeBase);
}
@GetMapping("findByEmployeId/id/{id}")
public SalaireEmploye findByEmployeId(@PathVariable Long id) {
	return salaireEmployeService.findByEmployeId(id);
}
@GetMapping("findByEmployeEmail/email/{email}")
public SalaireEmploye findByEmployeEmail(@PathVariable String email) {
	return salaireEmployeService.findByEmployeEmail(email);
}
@GetMapping("findByid/id/{id}")
public SalaireEmploye findByid(@PathVariable Long id) {
	return salaireEmployeService.findByid(id);
}
@GetMapping("findByMontantDeBase/montantDeBase/{montantDeBase}")
public List<SalaireEmploye> findByMontantDeBase(@PathVariable Double montantDeBase) {
	return salaireEmployeService.findBySalaireNet(montantDeBase);
}
@GetMapping("findByMonatntModifie/montantModifier/{montantModifier}")
public List<SalaireEmploye> findByMonatntModifie(@PathVariable Double montantModifier) {
	return salaireEmployeService.findByMonatntModifie(montantModifier);
}

@GetMapping("findAll")
public List<SalaireEmploye> findAll() {
	return salaireEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody SalaireEmploye salEmploye) {
	return salaireEmployeService.save(salEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return salaireEmployeService.deleteById(id);
}
}
