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

import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;
@RestController
@RequestMapping("/gestionDesEmployee-Api/User/")
public class UserRest {
@Autowired
private UserService userService;

@GetMapping("findByLogin/login/{login}")
public User findByLogin(@PathVariable String login) {
	return userService.findByLogin(login);
}

@GetMapping("findByid/id/{id}")
public User findByid(@PathVariable Long id) {
	return userService.findByid(id);
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
}
