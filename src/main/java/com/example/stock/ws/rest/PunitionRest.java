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

import com.example.stock.bean.Punition;
import com.example.stock.service.facade.PunitionService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Punition/")
public class PunitionRest {
@Autowired
private PunitionService punitionService;

@GetMapping("findByType/type/{type}")
public List<Punition> findByType(@PathVariable String type) {
	return punitionService.findByType(type);
}

@GetMapping("findByLibelle/libelle/{libelle}")
public Punition findByLibelle(@PathVariable String libelle) {
	return punitionService.findByLibelle(libelle);
}

@GetMapping("findByid/id/{id}")
public Punition findByid(@PathVariable Long id) {
	return punitionService.findByid(id);
}

@GetMapping("findAll")
public List<Punition> findAll() {
	return punitionService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Punition punition) {
	return punitionService.save(punition);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return punitionService.deleteById(id);
}
}
