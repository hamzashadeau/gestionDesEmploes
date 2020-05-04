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

import com.example.stock.bean.NoteEmploye;
import com.example.stock.service.facade.NoteEmployeService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/NoteEmploye/")
public class NoteEmployeRest {
@Autowired
private NoteEmployeService noteEmployeService;


@GetMapping("findByEmployeIdAndNoteGeneralDeAnneeId/id1/{id1}/id2/{id2}")
public List<NoteEmploye> findByEmployeIdAndNoteGeneralDeAnneeId(@PathVariable Long id1,@PathVariable Long id2) {
	return noteEmployeService.findByEmployeIdAndNoteGeneralDeAnneeId(id1, id2);
}


@GetMapping("findByid/id/{id}")
public NoteEmploye findByid(@PathVariable Long id) {
	return noteEmployeService.findByid(id);
}


@GetMapping("findAll")
public List<NoteEmploye> findAll() {
	return noteEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody NoteEmploye noteEmploye) {
	return noteEmployeService.save(noteEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return noteEmployeService.deleteById(id);
}
}
