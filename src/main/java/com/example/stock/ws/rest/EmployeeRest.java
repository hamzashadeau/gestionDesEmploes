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

import com.example.stock.bean.Employe;
import com.example.stock.service.facade.EmployeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Employee/")
public class EmployeeRest {
@Autowired
private EmployeService employeService;

@GetMapping("findLesEmployeAyantEvaluationAujourdHui")
public List<Employe> findLesEmployeAyantEvaluationAujourdHui() {
	return employeService.findLesEmployeAyantEvaluationAujourdHui();
}
@GetMapping("findLesEmployeAyantAvancementAujourdHui")
public List<Employe> findLesEmployeAyantAvancementAujourdHui() {
	return employeService.findLesEmployeAyantAvancementAujourdHui();
}
@GetMapping("findLesEmployeAyantLaNoteGeneraleAujourdHui")
public List<Employe> findLesEmployeAyantLaNoteGeneraleAujourdHui() {
	return employeService.findLesEmployeAyantLaNoteGeneraleAujourdHui();
}

@GetMapping("findByDateAvancementPrevue/dateAvancementPrevue/{dateAvancementPrevue}")
public List<Employe> findByDateAvancementPrevue(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date dateAvancementPrevue) {
	return employeService.findByDateAvancementPrevue(dateAvancementPrevue);
}

@GetMapping("findByDateDeProchainNote/dateDeProchainNote/{dateDeProchainNote}")
public List<Employe> findByDateDeProchainNote(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date dateDeProchainNote) {
	return employeService.findByDateDeProchainNote(dateDeProchainNote);
}

@GetMapping("findByDateProchainEvaluation/dateProchainEvaluation/{dateProchainEvaluation}")
public List<Employe> findByDateProchainEvaluation(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date dateProchainEvaluation) {
	return employeService.findByDateProchainEvaluation(dateProchainEvaluation);
}

@GetMapping("nombreDesEmployes")
public int nombreDesEmployes() {
	return employeService.nombreDesEmployes();
}

@GetMapping("nombreDesEmployesParDepartements/nomDepartement/{nomDepartement}")
public int nombreDesEmployesParDepartements(@PathVariable String nomDepartement) {
	return employeService.nombreDesEmployesParDepartements(nomDepartement);
}

@GetMapping("nombreDesEmployesParAnneeDeEntré/annee/{annee}")
public int nombreDesEmployesParAnneeDeEntré(@PathVariable Integer annee) {
	return employeService.nombreDesEmployesParAnneeDeEntré(annee);
}

@GetMapping("MoyenDeSalaireParAnnee/annee/{annee}")
public Double MoyenDeSalaireParAnnee(@PathVariable int annee) {
	return employeService.MoyenDeSalaireParAnnee(annee);
}

@GetMapping("EmployesParAnneeDeEntré/annee/{annee}")
public List<Employe> EmployesParAnneeDeEntré(@PathVariable Integer annee) {
	return employeService.EmployesParAnneeDeEntré(annee);
}
@GetMapping("findByCin/cin/{cin}")
public Employe findByCin(@PathVariable Integer cin) {
	return employeService.findByCin(cin);
}
@GetMapping("findByDoti/doti/{doti}")
public Employe findByDoti(@PathVariable Integer doti) {
	return employeService.findByDoti(doti);
}
@GetMapping("findByEmail/email/{email}")
public Employe findByEmail(@PathVariable String email) {
	return employeService.findByEmail(email);
}

@GetMapping("findBySupId/id/{id}")
public List<Employe> findBySupId(@PathVariable Long id) {
	return employeService.findBySupId(id);
}

@GetMapping("findAll")
public List<Employe> findAll() {
	return employeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody Employe employe) {
	return employeService.save(employe);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return employeService.deleteById(id);
}
@GetMapping("findByid/id/{id}")
public Employe findByid(@PathVariable Long id) {
	return employeService.findByid(id);
}
@GetMapping("findBySoldeRestantesCongéExceptionnel/soldeRestantesCongéExceptionnel/{soldeRestantesCongéExceptionnel}")
public List<Employe> findBySoldeRestantesCongéExceptionnel(@PathVariable Integer soldeRestantesCongéExceptionnel) {
	return employeService.findBySoldeRestantesCongéExceptionnel(soldeRestantesCongéExceptionnel);
}
}