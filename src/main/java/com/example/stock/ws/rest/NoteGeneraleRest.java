package com.example.stock.ws.rest;

import java.text.ParseException;
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

import com.example.stock.bean.Employe;
import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.service.facade.NoteGeneraleService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/NoteGeneralDeAnnee/")
public class NoteGeneraleRest {
@Autowired
private NoteGeneraleService noteGeneralDeAnneeService;

@GetMapping("findByDateAndEmployeDoti/date/{date}/doti/{doti}")
public NoteGeneralDeAnnee findByDateAndEmployeDoti(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date,@PathVariable Integer doti) {
	return noteGeneralDeAnneeService.findByDateAndEmployeDoti(date, doti);
}


@GetMapping("findByEmployeId/id/{id}")
public List<NoteGeneralDeAnnee> findByEmployeId(@PathVariable Long id) {
	return noteGeneralDeAnneeService.findByEmployeId(id);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<NoteGeneralDeAnnee> findByEmployeEmail(@PathVariable String email) {
	return noteGeneralDeAnneeService.findByEmployeEmail(email);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<NoteGeneralDeAnnee> findByEmployeDoti(@PathVariable Integer doti) {
	return noteGeneralDeAnneeService.findByEmployeDoti(doti);
}

@GetMapping("findByid/id/{id}")
public NoteGeneralDeAnnee findByid(@PathVariable Long id) {
	return noteGeneralDeAnneeService.findByid(id);
}


@GetMapping("findAll")
public List<NoteGeneralDeAnnee> findAll() {
	return noteGeneralDeAnneeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody NoteGeneralDeAnnee noteGeneralDeAnnee) {
	return noteGeneralDeAnneeService.save(noteGeneralDeAnnee);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return noteGeneralDeAnneeService.deleteById(id);
}
}
