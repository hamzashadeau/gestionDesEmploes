package com.example.stock.ws.rest;

import java.io.FileNotFoundException;
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
import com.itextpdf.text.DocumentException;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/NoteGeneralDeAnnee/")
public class NoteGeneraleRest {
@Autowired
private NoteGeneraleService noteGeneralDeAnneeService;

@PostMapping("RapportDesNoteePdf")
public int RapportDesNoteePdf(@RequestBody NoteGeneralDeAnnee note) throws DocumentException, FileNotFoundException {
	return noteGeneralDeAnneeService.RapportDesNoteePdf(note);
}


@GetMapping("findNoteNonTraite")
public List<NoteGeneralDeAnnee> findNoteNonTraite(String etat) {
	return noteGeneralDeAnneeService.findNoteNonTraite(etat);
}


@PostMapping("findNoteDeEmploye")
public List<NoteGeneralDeAnnee> findNoteDeEmploye(@RequestBody Employe employe) {
	return noteGeneralDeAnneeService.findNoteDeEmploye(employe);
}


@GetMapping("findByEtat/etat/{etat}")
public List<NoteGeneralDeAnnee> findByEtat(@PathVariable String etat) {
	return noteGeneralDeAnneeService.findByEtat(etat);
}


@GetMapping("findByDateAndEmployeDoti/date/{date}/doti/{doti}")
public NoteGeneralDeAnnee findByDateAndEmployeDoti(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date,@PathVariable Integer doti) {
	return noteGeneralDeAnneeService.findByDateAndEmployeDoti(date, doti);
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
