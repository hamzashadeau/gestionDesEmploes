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

import com.example.stock.bean.PrixEmploye;
import com.example.stock.service.facade.PrixEmployeService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/PrixEmploye/")
public class PrixEmployeRest {
@Autowired
private PrixEmployeService prixEmployeService;

@GetMapping("findByEmployeEmail/email/{email}")
public List<PrixEmploye> findByEmployeEmail(@PathVariable String email) {
	return prixEmployeService.findByEmployeEmail(email);
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
