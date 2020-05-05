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

import com.example.stock.bean.Revenu;
import com.example.stock.service.facade.RevenuService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Revenu/")
public class RevenuRest {
@Autowired
private RevenuService emoulumentsService;

@GetMapping("findByMontant/montant/{montant}")
public List<Revenu> findByMontant(@PathVariable Double montant) {
	return emoulumentsService.findByMontant(montant);
}

@GetMapping("findByLibelle/libelle/{libelle}")
public List<Revenu> findByLibelle(@PathVariable String libelle) {
	return emoulumentsService.findByLibelle(libelle);
}


@GetMapping("findByid/id/{id}")
public Revenu findByid(@PathVariable Long id) {
	return emoulumentsService.findByid(id);
}


@GetMapping("findAll")
public List<Revenu> findAll() {
	return emoulumentsService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Revenu emouluments) {
	return emoulumentsService.save(emouluments);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return emoulumentsService.deleteById(id);
}
}
