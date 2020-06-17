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

import com.example.stock.bean.TypeNotification;
import com.example.stock.service.facade.NotificationService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/Notification/")
public class NotificationRest {
@Autowired
private NotificationService notificationService;

@GetMapping("findByid/id/{id}")
public TypeNotification findByid(@PathVariable Long id) {
	return notificationService.findByid(id);
}
@GetMapping("findByLibelle/type/{type}")
public TypeNotification findByType(@PathVariable String libelle) {
	return notificationService.findByType(libelle);
}

@GetMapping("findAll")
public List<TypeNotification> findAll() {
	return notificationService.findAll();
}
@PostMapping("save")
public int save(@RequestBody TypeNotification fonction) {
	return notificationService.save(fonction);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return notificationService.deleteById(id);
}
}
