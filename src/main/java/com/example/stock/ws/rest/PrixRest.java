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

import com.example.stock.bean.Prix;
import com.example.stock.service.facade.PrixService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Prix/")
public class PrixRest {
@Autowired
private PrixService prixService;

@GetMapping("findByLibelle/libelle/{libelle}")
public Prix findByLibelle(@PathVariable String libelle) {
	return prixService.findByLibelle(libelle);
}

@GetMapping("findByid/id/{id}")
public Prix findByid(@PathVariable Long id) {
	return prixService.findByid(id);
}


@GetMapping("findAll")
public List<Prix> findAll() {
	return prixService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Prix prix) {
	return prixService.save(prix);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return prixService.deleteById(id);
}
}
