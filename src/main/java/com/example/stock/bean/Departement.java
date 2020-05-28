package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Departement {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String nom;
private String chefdoti;
private String fullname;
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getChefdoti() {
	return chefdoti;
}
public void setChefdoti(String chefdoti) {
	this.chefdoti = chefdoti;
}

public Departement(Long id, String nom, String chefdoti, String fullname) {
	super();
	this.id = id;
	this.nom = nom;
	this.chefdoti = chefdoti;
	this.fullname = fullname;
}

public Departement() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Departement [id=" + id + ", nom=" + nom + ", chefdoti=" + chefdoti + ", fullname=" + fullname + "]";
}

public String getFullname() {
	return fullname;
}

public void setFullname(String fullname) {
	this.fullname = fullname;
}



}
