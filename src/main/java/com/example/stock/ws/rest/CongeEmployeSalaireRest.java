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

import com.example.stock.bean.CongéEmployeSalaire;
import com.example.stock.bean.TypeCongee;
import com.example.stock.service.facade.CongeEmployeService;
import com.example.stock.service.facade.TypeCongeeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/CongeeEmployeSalaireService/")
public class CongeEmployeSalaireRest {
@Autowired
private CongeEmployeService congeEmployeService;

@GetMapping("findByCongeId/id/{id}")
public List<CongéEmployeSalaire> findByCongeId(@PathVariable Long id) {
	return congeEmployeService.findByCongeId(id);
}
@GetMapping("findByid/id/{id}")
public CongéEmployeSalaire findById(@PathVariable Long id) {
	return congeEmployeService.findByid(id);
}
@GetMapping("findAll")
public List<CongéEmployeSalaire> findAll() {
	return congeEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody CongéEmployeSalaire congéEmployeSalaire) {
	return congeEmployeService.save(congéEmployeSalaire);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return congeEmployeService.deleteById(id);
}

}
