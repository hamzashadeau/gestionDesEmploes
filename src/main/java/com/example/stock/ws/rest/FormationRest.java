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

import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;
import com.example.stock.service.facade.FormationService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Formation/")
public class FormationRest {
@Autowired
private FormationService formationService;

@GetMapping("findFormationDeEmploye")
public List<Formation> findFormationDeEmploye(@RequestBody Employe employe) {
	return formationService.findFormationDeEmploye(employe);
}

@GetMapping("findByemployéId/id/{id}")
public List<Formation> findByemployéId(@PathVariable Long id) {
	return formationService.findByemployéId(id);
}

@GetMapping("findByemployéEmail/email/{email}")
public List<Formation> findByemployéEmail(@PathVariable String email) {
	return formationService.findByemployéEmail(email);
}

@GetMapping("findByemployéDoti/doti/{doti}")
public List<Formation> findByemployéDoti(@PathVariable Integer doti) {
	return formationService.findByemployéDoti(doti);
}


@GetMapping("findByid/id/{id}")
public Formation findByid(@PathVariable Long id) {
	return formationService.findByid(id);
}


@GetMapping("findAll")
public List<Formation> findAll() {
	return formationService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Formation formation) {
	return formationService.save(formation);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return formationService.deleteById(id);
}
}
