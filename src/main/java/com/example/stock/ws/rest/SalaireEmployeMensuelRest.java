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

import com.example.stock.bean.SalaireEmployeMensuel;
import com.example.stock.service.facade.SalaireEmployeMensuelService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/SalaireEmployeMensuel/")
public class SalaireEmployeMensuelRest {
@Autowired
private SalaireEmployeMensuelService salaireEmployeMensuelService;


@GetMapping("findByEmployeDotiAndYearAndMois/doti/{doti}/year/{year}/mois/{mois}")
public List<SalaireEmployeMensuel> findByEmployeDotiAndYearAndMois(@PathVariable Integer doti,@PathVariable  Integer year,@PathVariable Integer mois) {
	return salaireEmployeMensuelService.findByEmployeDotiAndYearAndMois(doti, year, mois);
}
@GetMapping("findByEmployeDoti/doti/{doti}")
public List<SalaireEmployeMensuel> findByEmployeDoti(@PathVariable Integer doti) {
	return salaireEmployeMensuelService.findByEmployeDoti(doti);
}
@GetMapping("findByEmployeId/id/{id}")
public List<SalaireEmployeMensuel> findByEmployeId(@PathVariable Long id) {
	return salaireEmployeMensuelService.findByEmployeId(id);
}
@GetMapping("findByEmployeEmail/email/{email}")
public List<SalaireEmployeMensuel> findByEmployeEmail(@PathVariable String email) {
	return salaireEmployeMensuelService.findByEmployeEmail(email);
}
@GetMapping("findByid/id/{id}")
public SalaireEmployeMensuel findByid(@PathVariable Long id) {
	return salaireEmployeMensuelService.findByid(id);
}
@GetMapping("findByMonatntModifie/montantModifier/{montantModifier}")
public List<SalaireEmployeMensuel> findByMonatntModifie(@PathVariable Double montantModifier) {
	return salaireEmployeMensuelService.findByMonatntModifie(montantModifier);
}

@GetMapping("findAll")
public List<SalaireEmployeMensuel> findAll() {
	return salaireEmployeMensuelService.findAll();
}
@PostMapping("save")
public int save(@RequestBody SalaireEmployeMensuel salEmploye) {
	return salaireEmployeMensuelService.save(salEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return salaireEmployeMensuelService.deleteById(id);
}
}
