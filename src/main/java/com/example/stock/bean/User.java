package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String login;
private String password;

public User() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public User(String login, String password) {
	super();
	this.login = login;
	this.password = password;
}

@Override
public String toString() {
	return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
}



}
