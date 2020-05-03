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

import com.example.stock.bean.DepFonction;
import com.example.stock.service.facade.DepFonctionService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/DepFonction/")
public class DepFonctionRest {
@Autowired
private DepFonctionService depFonctionService;

@GetMapping("findByid/id/{id}")
public DepFonction findByid(@PathVariable Long id) {
	return depFonctionService.findByid(id);
}
@GetMapping("findByDepartemantNom/nomDepartemant/{nomDepartemant}")
public List<DepFonction> findByDepartemantNom(@PathVariable String nomDepartemant) {
	return depFonctionService.findByDepartemantNom(nomDepartemant);
}
@GetMapping("findByFonctionLibelle/libelle/{libelle}")
public List<DepFonction> findByFonctionLibelle(@PathVariable String libelle) {
	return depFonctionService.findByFonctionLibelle(libelle);
}

@GetMapping("findAll")
public List<DepFonction> findAll() {
	return depFonctionService.findAll();
}
@PostMapping("save")
public int save(@RequestBody DepFonction depFonction) {
	return depFonctionService.save(depFonction);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return depFonctionService.deleteById(id);
}

}
