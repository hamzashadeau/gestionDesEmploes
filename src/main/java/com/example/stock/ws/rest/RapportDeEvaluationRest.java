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

import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.RapportDeEvaluationService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/RapportDeEvaluation/")
public class RapportDeEvaluationRest {
@Autowired
private RapportDeEvaluationService emoulumentsService;



@PostMapping("update")
public int update(@RequestBody RapportDeEvaluation rapportDeEvaluation) {
	return emoulumentsService.update(rapportDeEvaluation);
}

@GetMapping("findByNouveauGradeIdAndEmployeDoti/id/{id}/doti/{doti}")
public RapportDeEvaluation findByNouveauGradeIdAndEmployeDoti(@PathVariable Long id,@PathVariable Integer doti) {
	return emoulumentsService.findByNouveauGradeIdAndEmployeDoti(id, doti);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<RapportDeEvaluation> findByEmployeEmail(@PathVariable String email) {
	return emoulumentsService.findByEmployeEmail(email);
}

@GetMapping("findByNouveauGradeId/id/{id}")
public List<RapportDeEvaluation> findByNouveauGradeId(@PathVariable Long id) {
	return emoulumentsService.findByNouveauGradeId(id);
}

@GetMapping("findByEmployeDoti/doti/{doti}")
public List<RapportDeEvaluation> findByEmployeDoti(@PathVariable Integer doti) {
	return emoulumentsService.findByEmployeDoti(doti);
}

@GetMapping("findByEmployeId/id/{id}")
public List<RapportDeEvaluation> findByEmployeId(@PathVariable Long id) {
	return emoulumentsService.findByEmployeId(id);
}


@GetMapping("findByid/id/{id}")
public RapportDeEvaluation findByid(@PathVariable Long id) {
	return emoulumentsService.findByid(id);
}


@GetMapping("findAll")
public List<RapportDeEvaluation> findAll() {
	return emoulumentsService.findAll();
}
@PostMapping("save")
public int save(@RequestBody RapportDeEvaluation emouluments) {
	return emoulumentsService.save(emouluments);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return emoulumentsService.deleteById(id);
}
}
