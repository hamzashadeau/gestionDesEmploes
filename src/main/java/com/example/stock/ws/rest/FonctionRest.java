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

import com.example.stock.bean.Fonction;
import com.example.stock.service.facade.FonctionService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/Fonction/")
public class FonctionRest {
@Autowired
private FonctionService fonctionService;

@GetMapping("findByid/id/{id}")
public Fonction findByid(@PathVariable Long id) {
	return fonctionService.findByid(id);
}
@GetMapping("findByLibelle/libelle/{libelle}")
public Fonction findByLibelle(@PathVariable String libelle) {
	return fonctionService.findByLibelle(libelle);
}

@GetMapping("findAll")
public List<Fonction> findAll() {
	return fonctionService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Fonction fonction) {
	return fonctionService.save(fonction);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return fonctionService.deleteById(id);
}
}
