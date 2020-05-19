package com.example.stock.ws.rest;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Date;
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

import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/gestionDesEmployee-Api/User/")
public class UserRest {
@Autowired
private UserService userService;

@PostMapping("seConnecter")
public int seConnecter(@RequestBody User user) throws Exception {
	return userService.seConnecter(user);
}

@GetMapping("findByLogin/login/{login}")
public User findByLogin(@PathVariable String login) {
	return userService.findByLogin(login);
}

@GetMapping("findByid/id/{id}")
public User findByid(@PathVariable Long id) {
	return userService.findByid(id);
}

@GetMapping("hash/mdp/{mdp}")
public static String hash(@PathVariable String mdp) throws Exception {
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	md.update(mdp.getBytes());
	byte byteData[] = md.digest();
StringBuffer hexString = new StringBuffer();
for (int i = 0; i < byteData.length; i++) {
String hex = Integer.toHexString(0xff & byteData[i]);
if(hex.length()==1) hexString.append('0');
hexString.append(hex);
}
System.out.println("En format hexa:" + hexString.toString());
return hexString.toString();
}

@GetMapping("findAll")
public List<User> findAll() {
	return userService.findAll();
}
@PostMapping("save")
public int save(@RequestBody User user) {
	return userService.save(user);
}
@DeleteMapping("deleteById/id/{id}")
public int deleteById(@PathVariable Long id) {
	return userService.deleteById(id);
}
@GetMapping("getDateEvaluationDeGrade")
public static Date getDateEvaluationDeGrade() {
	BigDecimal big = new BigDecimal("31557600000");
	Long nombreAnnee = null;
	switch ("grade3") {
	case "grade1":
		nombreAnnee = 1 * big.longValue() ;
		break;
	case "grade2":
		nombreAnnee =  1 * big.longValue();
		break;
	case "grade3":
		nombreAnnee =  2 * big.longValue();
		break;
	case "grade4":
		nombreAnnee =   2 * big.longValue();
		break;
	case "grade5":
		nombreAnnee =   2 * big.longValue();
		break;
	case "grade6":
		nombreAnnee =   3 * big.longValue();
		break;
	case "grade7":
		nombreAnnee =   3 * big.longValue();
		break;
	case "grade8":
		nombreAnnee =   3 * big.longValue();
		break;
	case "grade9":
		nombreAnnee =   3 * big.longValue();
		break;
	case "grade10":
		nombreAnnee =   2 * big.longValue();
		break;
	}
	return DateUlils.getDateEvaluation(new Date(), nombreAnnee);
}

}
