package com.example.stock.ws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.Congé;
import com.example.stock.service.facade.CongeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/conge/")
public class CongeRest {
@Autowired
private CongeService congeService;

@GetMapping("findByCongeeLibelle/libelle/{libelle}")
public List<Congé> findByCongeeLibelle(@PathVariable String libelle) {
	return congeService.findByCongeeLibelle(libelle);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<Congé> findByEmployeEmail(@PathVariable String email) {
	return congeService.findByEmployeEmail(email);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<Congé> findByEmployeDoti(@PathVariable Integer doti) {
	return congeService.findByEmployeDoti(doti);
}

@GetMapping("findByEtat/etat/{etat}")
public List<Congé> findByEtat(@PathVariable String etat) {
	return congeService.findByEtat(etat);
}

@PostMapping("save")
public int save(@RequestBody Congé conge) {
	return congeService.save(conge);
}

@GetMapping("findByid/id/{id}")
public Congé findByid(@PathVariable Long id) {
	return congeService.findByid(id);
}
}
