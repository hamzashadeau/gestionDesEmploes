package com.example.stock.ws.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.service.facade.PermanenceAdministrativeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/PermanenceAdministrative/")
public class PermanenceAdministrativeRest {
@Autowired
private PermanenceAdministrativeService permanenceAdministrativeService;

@GetMapping("findByAnnee/annee/{annee}")
public List<PermanenceAdministrative> findByAnnee(@PathVariable Integer annee) {
	return permanenceAdministrativeService.findByAnnee(annee);
}

@GetMapping("findByAnneeAndemployeDoti/annee/{annee}/doti/{doti}")
public List<PermanenceAdministrative> findByAnneeAndemployeDoti(@PathVariable Integer annee,@PathVariable String doti) {
	return permanenceAdministrativeService.findByAnneeAndemployeDoti(annee, doti);
}

@GetMapping("update")
public int update(@RequestBody PermanenceAdministrative permanenceAdministrative) {
	return permanenceAdministrativeService.update(permanenceAdministrative);
}

@GetMapping("findByDate/date/{date}")
public PermanenceAdministrative findByDate(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date) {
	return permanenceAdministrativeService.findByDate(date);
}

@GetMapping("findByEmployeId/id/{id}")
public List<PermanenceAdministrative> findByEmployeId(@PathVariable Long id) {
	return permanenceAdministrativeService.findByEmployeId(id);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<PermanenceAdministrative> findByEmployeEmail(@PathVariable String email) {
	return permanenceAdministrativeService.findByEmployeEmail(email);
}

@GetMapping("findByemployeDoti/doti/{doti}")
public List<PermanenceAdministrative> findByemployeDoti(@PathVariable String doti) {
	return permanenceAdministrativeService.findByemployeDoti(doti);
}

@GetMapping("findByPeriode/periode/{periode}")
public List<PermanenceAdministrative> findByPeriode(@PathVariable Integer periode) {
	return permanenceAdministrativeService.findByPeriode(periode);
}

@GetMapping("findByid/id/{id}")
public PermanenceAdministrative findByid(@PathVariable Long id) {
	return permanenceAdministrativeService.findByid(id);
}

@GetMapping("findAll")
public List<PermanenceAdministrative> findAll() {
	return permanenceAdministrativeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody PermanenceAdministrative permanenceAdministrative) {
	return permanenceAdministrativeService.save(permanenceAdministrative);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return permanenceAdministrativeService.deleteById(id);
}
}
