package com.example.stock.ws.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.bean.NoteGeneralDeAnnee;
import com.example.stock.service.facade.NoteGeneraleService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/NoteGeneralDeAnnee/")
public class NoteGeneraleRest {
@Autowired
private NoteGeneraleService noteGeneralDeAnneeService;

@GetMapping("findByEmployéId/id/{id}")
public List<NoteGeneralDeAnnee> findByEmployéId(@PathVariable Long id) {
	return noteGeneralDeAnneeService.findByEmployéId(id);
}

@GetMapping("findByEmployéEmail/email/{email}")
public List<NoteGeneralDeAnnee> findByEmployéEmail(@PathVariable String email) {
	return noteGeneralDeAnneeService.findByEmployéEmail(email);
}

@GetMapping("findByEmployéDoti/doti/{doti}")
public List<NoteGeneralDeAnnee> findByEmployéDoti(@PathVariable Integer doti) {
	return noteGeneralDeAnneeService.findByEmployéDoti(doti);
}

@GetMapping("findByDate/date/{date}")
public List<NoteGeneralDeAnnee> findByDate(@PathVariable  @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date) {
	return noteGeneralDeAnneeService.findByDate(date);
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
