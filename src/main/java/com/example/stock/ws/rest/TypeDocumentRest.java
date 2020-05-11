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

import com.example.stock.bean.TypeDocument;
import com.example.stock.service.facade.TypeDocumentService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/TypeDocument/")
public class TypeDocumentRest {
@Autowired
private TypeDocumentService typeCongeeService;

@GetMapping("findByid/id/{id}")
public TypeDocument findByid(@PathVariable Long id) {
	return typeCongeeService.findByid(id);
}
@GetMapping("findByType/type/{type}")
public TypeDocument findByLibelle(@PathVariable String type) {
	return typeCongeeService.findByLibelle(type);
}
@GetMapping("findAll")
public List<TypeDocument> findAll() {
	return typeCongeeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody TypeDocument typeCongee) {
	return typeCongeeService.save(typeCongee);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return typeCongeeService.deleteById(id);
}

}
