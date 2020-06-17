package com.example.stock.ws.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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

import com.example.stock.bean.NotificationEmploye;
import com.example.stock.service.facade.NotificationEmployeService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/NotificationEmploye/")
public class NotificationEmployeRest {
@Autowired
private NotificationEmployeService notificationEmployeService;

@GetMapping("findNotificationPaDate/date/{date}")
public List<NotificationEmploye> findNotificationPaDate(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date date) {
	return notificationEmployeService.findNotificationPaDate(date);
}

@GetMapping("findNotificationAujourdhui")
public List<NotificationEmploye> findNotificationAujourdhui() {
	return notificationEmployeService.findNotificationAujourdhui();
}

@GetMapping("sendmail/email/{email}/subject/{subject}/content/{content}")
public int sendmail(@PathVariable String email,@PathVariable String subject,@PathVariable String content) throws AddressException, MessagingException, IOException {
return	notificationEmployeService.sendmail(email, subject, content);
}

@GetMapping("findByEmployeId/id/{id}")
public List<NotificationEmploye> findByEmployeId (@PathVariable Long id) {
	return notificationEmployeService.findByEmployeId(id);
}

@GetMapping("findByEmployeEmail/email/{email}")
public List<NotificationEmploye> findByEmployeEmail(@PathVariable String email) {
	return notificationEmployeService.findByEmployeEmail(email);
}

@GetMapping("findByemployeDoti/doti/{doti}")
public List<NotificationEmploye> findByemployeDoti(@PathVariable String doti) {
	return notificationEmployeService.findByemployeDoti(doti);
}

@GetMapping("findByDateDeObtenation/dateDeObtenation/{dateDeObtenation}")
public List<NotificationEmploye> findByDateDeNotification(@PathVariable @DateTimeFormat(pattern =  "yyyy-MM-dd") Date dateDeObtenation) {
	return notificationEmployeService.findByDateDeNotification(dateDeObtenation);
}

@GetMapping("findByid/id/{id}")
public NotificationEmploye findByid(@PathVariable Long id) {
	return notificationEmployeService.findByid(id);
}
@GetMapping("findAll")
public List<NotificationEmploye> findAll() {
	return notificationEmployeService.findAll();
}
@PostMapping("save")
public int save(@RequestBody NotificationEmploye notificationEmploye) {
	return notificationEmployeService.save(notificationEmploye);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return notificationEmployeService.deleteById(id);
}
}
